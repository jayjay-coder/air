package org.jayjay.air.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.util.AccessAddressUtils;
import org.jayjay.air.common.util.CommonUtils;
import org.jayjay.air.common.util.ResponseUtils;
import org.jayjay.air.security.config.JwtConfig;
import org.jayjay.air.security.util.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: JwTAuthenticationFilter
 * @Description: JWT权限过滤器，用于验证Token是否合法
 */
@Slf4j
public class JwtAuthenticationFilter  extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
//        // 取出Token
//        String token = request.getHeader(JwtConfig.tokenHeader);
//
//        if (token != null && token.startsWith(JwtConfig.tokenPrefix)) {
//            SysUserDetails sysUserDetails = JwtTokenUtils.parseAccessToken(token);
//
//            if (sysUserDetails != null) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        sysUserDetails, sysUserDetails.getId(), sysUserDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        filterChain.doFilter(request, response);
        // 取出Token
        String token = request.getHeader(JwtConfig.tokenHeader);
        String requestURL = request.getServletPath();

        if (token != null && token.startsWith(JwtConfig.tokenPrefix) && !isWhiteList(requestURL)) {
            String accessTokenKey = JwtTokenUtils.getAccessTokenKey(token);
            String refreshTokenKey = JwtTokenUtils.getRefreshTokenKey(token);
            // 是否过期
            if (JwtTokenUtils.isExpiration(JwtTokenUtils.getExpirationByToken(token))) {
                ResponseUtils.responseJson(response, ResultModel.error(505, "Token已失效", "Token已进入黑名单"));
                return;
            }

            // 是否存在于Redis中
            if (JwtTokenUtils.hasToken(accessTokenKey)) {
                String ip = AccessAddressUtils.getIpAddress(request);
                String expiration = JwtTokenUtils.getExpirationByToken(token);
                String username = JwtTokenUtils.getUserNameByToken(token);

                // 判断是否过期
                if (JwtTokenUtils.isExpiration(expiration)) {
//                    // 删除token
//                    JwtTokenUtils.addBlackList(token);
                    JwtTokenUtils.deleteRedisToken(accessTokenKey);

                    if(JwtTokenUtils.hasToken(refreshTokenKey)) {
                        // 是否在刷新期内
                        String refreshExpiration = JwtTokenUtils.getExpirationByToken(refreshTokenKey);
                        if (JwtTokenUtils.isExpiration(refreshExpiration)) {
                            // 刷新Token，重新存入请求头
                            String newToke = JwtTokenUtils.refreshAccessToken(token);

                            // 删除旧的Token，并保存新的Token
                            JwtTokenUtils.deleteRedisToken(accessTokenKey);
                            JwtTokenUtils.setAccessTokenInfo(newToke, username, ip);
                            response.setHeader(JwtConfig.tokenHeader, newToke);

                            log.info("用户{}的Token已过期，但为超过刷新时间，已刷新", username);

                            token = newToke;
                        } else {

                            log.info("用户{}的Token已过期且超过刷新时间，不予刷新", username);

                            // 加入黑名单
//                            JwtTokenUtils.addBlackList(token);
                            JwtTokenUtils.deleteRedisToken(accessTokenKey);
                            JwtTokenUtils.deleteRedisToken(refreshTokenKey);
                            ResponseUtils.responseJson(response, ResultModel.error(505, "Token已过期", "已超过刷新有效期"));
                            return;
                        }
                    }
                }

                SysUserDetails sysUserDetails = JwtTokenUtils.parseAccessToken(token);

                if (sysUserDetails != null) {
                    // 校验IP
                    if (!StringUtils.equals(ip, sysUserDetails.getIp())) {

                        log.info("用户{}请求IP与Token中IP信息不一致", username);

                        // 加入黑名单
//                        JwtTokenUtils.addBlackList(token);
                        JwtTokenUtils.deleteRedisToken(accessTokenKey);
                        JwtTokenUtils.deleteRedisToken(refreshTokenKey);
                        ResponseUtils.responseJson(response, ResultModel.error(505, "Token已过期", "可能存在IP伪造风险"));
                        return;
                    }

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            sysUserDetails, sysUserDetails.getId(), sysUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 是否白名单
     * @param url
     * @return
     */
    private boolean isWhiteList(String url){
        if(StringUtils.isBlank(JwtConfig.whiteList)){
            return false;
        }
        return Arrays.stream(JwtConfig.whiteList.split(",")).anyMatch(item-> CommonUtils.match(item,url));
    }

}
