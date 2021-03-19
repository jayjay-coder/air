package org.jayjay.air.security.controller;

import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.security.config.JwtConfig;
import org.jayjay.air.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JayJay
 * @Date: 19/3/2021
 * @ClassName: JwtTokenController
 * @Description:
 */
@RestController
@RequestMapping("/token")
public class JwtTokenController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/refresh")
    public ResultModel refresh(HttpServletRequest request){
        // 取出Token
        String token = request.getHeader("refreshToken");
        String newToken = jwtTokenService.refresh(token);
        Map<String,String> result = new HashMap<>();
        result.put("token",newToken);
        return ResultModel.success(result);
    }
//    @GetMapping("/refresh")
//    public ResultModel refresh(String refreshToken){
//        jwtTokenService.refresh(refreshToken);
//        return ResultModel.success();
//    }
}
