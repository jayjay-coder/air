package org.jayjay.air.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.jayjay.air.common.constant.ResultCode;
import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.util.ResponseUtils;
import org.jayjay.air.security.config.JwtConfig;
import org.jayjay.air.security.util.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: UserLogoutSuccessHandler
 * @Description: 登出成功处理类
 */
@Component
@Slf4j
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        // 添加到黑名单
        String token = request.getHeader(JwtConfig.tokenHeader);
        JwtTokenUtils.addBlackList(token);

        log.info("用户{}登出成功，Token信息已保存到Redis的黑名单中", JwtTokenUtils.getUserNameByToken(token));

        SecurityContextHolder.clearContext();
        ResponseUtils.responseJson(response, ResultModel.success(ResultCode.SUCCESS.getCode(), "登出成功", null));
    }
}