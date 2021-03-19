package org.jayjay.air.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.common.dto.TokenDto;
import org.jayjay.air.common.exception.CustomException;
import org.jayjay.air.security.service.JwtTokenService;
import org.jayjay.air.security.util.JwtTokenUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: JayJay
 * @Date: 16/3/2021
 * @ClassName: TokenServiceImpl
 * @Description:
 */
@Service
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService {
    /**
     * 刷新token
     *
     * @param refreshToken
     * @return
     */
    @Override
    public String refresh(String refreshToken) {
        LocalDateTime now = LocalDateTime.now();
        String refreshTokenKey = JwtTokenUtils.getRefreshTokenKey(refreshToken);
        boolean b = JwtTokenUtils.hasToken(refreshTokenKey);
        if (!b) {
            throw new CustomException(505, "token已失效,请重新登录");
        }
        TokenDto tokenDto = JwtTokenUtils.getTokenDto(refreshTokenKey);
        if (now.isAfter(tokenDto.getExpirationTime())) {
            throw new CustomException(505, "token已失效,请重新登录");
        }
        SysUserDetails sysUserDetails = JwtTokenUtils.parseToken(refreshToken);
        String accessToken = JwtTokenUtils.createAccessToken(sysUserDetails);
        JwtTokenUtils.setAccessTokenInfo(accessToken, sysUserDetails.getUsername(), sysUserDetails.getIp());
        log.info("{}刷新token成功",sysUserDetails.getUsername());
        return accessToken;
    }
}
