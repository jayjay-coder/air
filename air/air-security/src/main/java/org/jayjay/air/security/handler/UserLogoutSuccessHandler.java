package org.jayjay.air.security.handler;

import org.jayjay.air.common.constant.ResultCode;
import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.util.ResponseUtils;
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
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        SecurityContextHolder.clearContext();
        ResponseUtils.responseJson(response, ResultModel.success(ResultCode.SUCCESS.getCode(), "登出成功", null));
    }
}