package org.jayjay.air.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.jayjay.air.common.constant.ResultCode;
import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.util.AccessAddressUtils;
import org.jayjay.air.common.util.ResponseUtils;
import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.security.util.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: UserLoginSuccessHandler
 * @Description: 登录成功处理类
 */
@Component
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {

        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        // 获得请求IP
        String ip = AccessAddressUtils.getIpAddress(request);
        sysUserDetails.setIp(ip);
        String token = JwtTokenUtils.createAccessToken(sysUserDetails);

        // 保存Token信息到Redis中
        JwtTokenUtils.setTokenInfo(token, sysUserDetails.getUsername(), ip);

        log.info("用户{}登录成功，Token信息已保存到Redis", sysUserDetails.getUsername());
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        ResponseUtils.responseJson(response, ResultModel.success(ResultCode.SUCCESS.getCode(), "登录成功", tokenMap));
    }
}
