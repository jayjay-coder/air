package org.jayjay.air.security.filter;

import org.jayjay.air.security.config.JwtConfig;
import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.security.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: JwTAuthenticationFilter
 * @Description: JWT权限过滤器，用于验证Token是否合法
 */
public class JwtAuthenticationFilter  extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // 取出Token
        String token = request.getHeader(JwtConfig.tokenHeader);

        if (token != null && token.startsWith(JwtConfig.tokenPrefix)) {
            SysUserDetails sysUserDetails = JwtTokenUtil.parseAccessToken(token);

            if (sysUserDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        sysUserDetails, sysUserDetails.getId(), sysUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
