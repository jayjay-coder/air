package org.jayjay.air.security.service;

/**
 * @Author: JayJay
 * @Date: 16/3/2021
 * @ClassName: TokenService
 * @Description: token业务层
 */
public interface JwtTokenService {

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    String refresh(String refreshToken);
}
