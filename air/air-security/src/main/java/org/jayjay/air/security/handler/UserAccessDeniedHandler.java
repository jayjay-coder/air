package org.jayjay.air.security.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jayjay.air.common.constant.ResultCode;
import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.util.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: UserAccessDeniedHandler
 * @Description: 无权限处理类
 */
@Component
@Slf4j
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.responseJson(response, ResultModel.error(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMsg(), accessDeniedException.getMessage()));
    }

}