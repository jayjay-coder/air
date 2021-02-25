package org.jayjay.air.security.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jayjay.air.common.constant.ResultCode;
import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.util.ResponseUtils;
import org.jayjay.air.security.util.JwtTokenUtil;
import org.jayjay.air.security.config.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
        String token = JwtTokenUtil.createAccessToken(sysUserDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        ResponseUtils.responseJson(response, ResultModel.success(ResultCode.SUCCESS.getCode(), "登录成功", tokenMap));
    }
}
