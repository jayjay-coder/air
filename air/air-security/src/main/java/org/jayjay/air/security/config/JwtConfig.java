package org.jayjay.air.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: JayJay
 * @Date: 24/2/2021
 * @ClassName: JwtConifg
 * @Description: JWT配置基础类
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@SuppressWarnings("static-access")
public class JwtConfig {

    /**
     * 密匙Key
     */
    public static String secret;

    /**
     * HeaderKey
     */
    public static String tokenHeader;

    /**
     * Token前缀
     */
    public static String tokenPrefix;

    /**
     * redis token前缀
     */
    public static String accessTokenPrefix;

    /**
     * 过期时间
     */
    public static Long expiration;

    /**
     * redis refreshToken前缀
     */
    public static String refreshTokenPrefix;

    /**
     * 有效时间
     */
    public static Long refreshTime;

    /**
     * 配置白名单
     */
    public static String whiteList;

    /**
     * 将过期时间单位换算成毫秒
     *
     * @param expiration 过期时间，单位秒
     */
    public void setExpiration(Long expiration) {
        this.expiration = expiration * 1000;
    }

    /**
     * 将有效时间单位换算成毫秒
     *
     * @param refreshTime 有效时间，单位秒
     */
    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime * 24 * 60 * 60 * 1000;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix + " ";
    }


    public void setWhiteList(String whiteList) {
        this.whiteList = whiteList;
    }

    public  void setAccessTokenPrefix(String accessTokenPrefix) {
        this.accessTokenPrefix = accessTokenPrefix;
    }

    public  void setRefreshTokenPrefix(String refreshTokenPrefix) {
        this.refreshTokenPrefix = refreshTokenPrefix;
    }
}
