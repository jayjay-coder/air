package org.jayjay.air.security.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jayjay.air.common.dto.TokenDto;
import org.jayjay.air.common.util.CommonUtils;
import org.jayjay.air.common.util.RedisUtils;
import org.jayjay.air.security.config.JwtConfig;
import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.security.service.impl.SysUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @Author: JayJay
 * @Date: 24/2/2021
 * @ClassName: JWTTokenUtil
 * @Description: JWT生产Token工具类
 */
@Slf4j
@Component
public class JwtTokenUtils {
    /**
     * 时间格式化
     */
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SysUserDetailsService sysUserDetailsService;

    private static JwtTokenUtils jwtTokenUtils;

    @PostConstruct
    public void init() {
        jwtTokenUtils = this;
        jwtTokenUtils.sysUserDetailsService = this.sysUserDetailsService;
    }

    /**
     * 创建Token
     *
     * @param sysUserDetails 用户信息
     * @return
     */
    public static String createAccessToken(SysUserDetails sysUserDetails) {
        String token = Jwts.builder()// 设置JWT
                .setId(sysUserDetails.getId()) // 用户Id
                .setSubject(sysUserDetails.getUsername()) // 主题
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer("JayJay") // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.expiration)) // 过期时间
                .signWith(SignatureAlgorithm.HS512, JwtConfig.secret) // 签名算法、密钥
                .claim("authorities", JSON.toJSONString(sysUserDetails.getAuthorities()))// 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
                .claim("ip", sysUserDetails.getIp()).compact();
        return JwtConfig.tokenPrefix + token;
    }

    /**
     * 生成刷新token
     *
     * @param sysUserDetails
     * @return
     */
    public static String createRefreshToken(SysUserDetails sysUserDetails) {
        String token = Jwts.builder()// 设置JWT
                .setId(sysUserDetails.getId()) // 用户Id
                .setSubject(sysUserDetails.getUsername()) // 主题
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer("JayJay") // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.refreshTime * 24 * 60 * 60)) // 过期时间
                .signWith(SignatureAlgorithm.HS512, JwtConfig.secret) // 签名算法、密钥
                .claim("authorities", JSON.toJSONString(sysUserDetails.getAuthorities()))// 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
                .claim("ip", sysUserDetails.getIp()).compact();
        return JwtConfig.tokenPrefix + token;
    }


    /**
     * 刷新Token
     *
     * @param oldToken 过期但未超过刷新时间的Token
     * @return
     */
    public static String refreshAccessToken(String oldToken) {
        String username = JwtTokenUtils.getUserNameByToken(oldToken);
        SysUserDetails sysUserDetails = (SysUserDetails) jwtTokenUtils.sysUserDetailsService
                .loadUserByUsername(username);
        sysUserDetails.setIp(JwtTokenUtils.getIpByToken(oldToken));
        return createAccessToken(sysUserDetails);
    }

    /**
     * 解析Token
     *
     * @param token Token信息
     * @return
     */
    public static SysUserDetails parseAccessToken(String token) {
        SysUserDetails sysUserDetails = null;
        if (StringUtils.isNotEmpty(token)) {
            try {
                // 去除JWT前缀
                token = token.substring(JwtConfig.tokenPrefix.length());

                // 解析Token
                Claims claims = Jwts.parser().setSigningKey(JwtConfig.secret).parseClaimsJws(token).getBody();

                // 获取用户信息
                sysUserDetails = new SysUserDetails();
                sysUserDetails.setId(claims.getId());
                sysUserDetails.setUserName(claims.getSubject());

                // 获取角色
                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                String authority = claims.get("authorities").toString();
                if (StringUtils.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority,
                            new TypeReference<List<Map<String, String>>>() {
                            });
                    for (Map<String, String> role : authorityList) {
                        if (!role.isEmpty()) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }

                sysUserDetails.setAuthorities(authorities);

                // 获取IP
                String ip = claims.get("ip").toString();
                sysUserDetails.setIp(ip);
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return sysUserDetails;
    }


    /**
     * 解析出accessTokenKey
     *
     * @param token
     * @return
     */
    public static String getAccessTokenKey(String token) {
        SysUserDetails sysUserDetails = parseAccessToken(token);
        if (sysUserDetails == null) {
            return null;
        }
        String tokenKey = JwtConfig.accessTokenPrefix + sysUserDetails.getUsername() + "_" + sysUserDetails.getIp();
        return tokenKey;
    }

    /**
     * 解析出refreshTokenKey
     *
     * @param token
     * @return
     */
    public static String getRefreshTokenKey(String token) {
        SysUserDetails sysUserDetails = parseAccessToken(token);
        if (sysUserDetails == null) {
            return null;
        }
        String tokenKey = JwtConfig.refreshTokenPrefix + sysUserDetails.getUsername() + "_" + sysUserDetails.getIp();
        return tokenKey;
    }


    /**
     * 保存Token信息到Redis中
     *
     * @param token    Token信息
     * @param username 用户名
     * @param ip       IP
     */
    public static void setAccessTokenInfo(String token, String username, String ip) {
        if (StringUtils.isNotEmpty(token)) {
            // 去除JWT前缀
            token = token.substring(JwtConfig.tokenPrefix.length());

            Long expirationTime = JwtConfig.expiration;
            LocalDateTime localDateTime = LocalDateTime.now();
            String tokenKey = JwtConfig.accessTokenPrefix + username + "_" + ip;
            log.info(tokenKey);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setUsername(username);
            tokenDto.setIp(ip);
            tokenDto.setCreateTime(localDateTime);
            tokenDto.setExpirationTime(localDateTime.plusMinutes(expirationTime));
            RedisUtils.set(tokenKey,tokenDto);

        }
    }

    public static void setRefreshTokenInfo(String token, String username, String ip) {
        if (StringUtils.isNotEmpty(token)) {
            // 去除JWT前缀
            token = token.substring(JwtConfig.tokenPrefix.length());

            Long expirationTime = JwtConfig.refreshTime;
            LocalDateTime localDateTime = LocalDateTime.now();
            String tokenKey = JwtConfig.refreshTokenPrefix + username + "_" + ip;
            SysUserDetails currentUser = CommonUtils.getCurrentUser();
            log.info(tokenKey);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setUsername(username);
            tokenDto.setIp(ip);
            tokenDto.setCreateTime(localDateTime);
            tokenDto.setExpirationTime(localDateTime.plusMinutes(expirationTime));
            RedisUtils.set(tokenKey,tokenDto);
        }
    }

    /**
     * 获取tokenDto
     * @param tokenKey
     * @return
     */
    public static TokenDto getTokenDto(String tokenKey){
        if (StringUtils.isNotEmpty(tokenKey)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            TokenDto tokenDto = RedisUtils.get(tokenKey);
            return Optional.ofNullable(tokenDto).orElse(null);
        }
        return null;
    }

    /**
     * 将Token放到黑名单中
     *
     * @param token Token信息
     */
    public static void addBlackList(String token) {
        if (StringUtils.isNotEmpty(token)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            String tokenKey = getAccessTokenKey(token);
            RedisUtils.hset("blackList", tokenKey, df.format(LocalDateTime.now()));
        }
    }

    /**
     * Redis中删除Token
     *
     * @param tokenKey Tokenkey
     */
    public static void deleteRedisToken(String tokenKey) {
        if (StringUtils.isNotEmpty(tokenKey)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            RedisUtils.deleteKey(tokenKey);
        }
    }

    /**
     * 判断当前Token是否在黑名单中
     *
     * @param tokenKey tokenKey
     */
    public static boolean isBlackList(String tokenKey) {
        if (StringUtils.isNotEmpty(tokenKey)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            return RedisUtils.hasKey("blackList", tokenKey);
        }
        return false;
    }

    /**
     * 是否过期
     *
     * @param expiration 过期时间，字符串
     * @return 过期返回True，未过期返回false
     */
    public static boolean isExpiration(String expiration) {
        LocalDateTime expirationTime = LocalDateTime.parse(expiration, df);
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.compareTo(expirationTime) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否有效
     *
     * @param refreshTime 刷新时间，字符串
     * @return 有效返回True，无效返回false
     */
    public static boolean isValid(String refreshTime) {
        LocalDateTime validTime = LocalDateTime.parse(refreshTime, df);
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.compareTo(validTime) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 检查Redis中是否存在Token
     *
     * @param tokenKey tokenKey
     * @return
     */
    public static boolean hasToken(String tokenKey) {
        if (StringUtils.isNotEmpty(tokenKey)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            return RedisUtils.hasKey(tokenKey);
        }
        return false;
    }

    /**
     * 从Redis中获取过期时间
     *
     * @param tokenKey tokenKey
     * @return 过期时间，字符串
     */
    public static String getExpirationByToken(String tokenKey) {
        if (StringUtils.isNotEmpty(tokenKey)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            TokenDto tokenDto = RedisUtils.get(tokenKey);
            return tokenDto != null ? tokenDto.getExpirationTime().format(df) : null;
        }
        return null;
    }


    /**
     * 从Redis中获取用户名
     *
     * @param tokenKey tokenKey
     * @return
     */
    public static String getUserNameByToken(String tokenKey) {
        if (StringUtils.isNotEmpty(tokenKey)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            TokenDto tokenDto = RedisUtils.get(tokenKey);
            return tokenDto != null ? tokenDto.getUsername() : null;
        }
        return null;
    }

    /**
     * 从Redis中获取IP
     *
     * @param tokenKey tokenKey
     * @return
     */
    public static String getIpByToken(String tokenKey) {
        if (StringUtils.isNotEmpty(tokenKey)) {
            // 去除JWT前缀
//            token = token.substring(JwtConfig.tokenPrefix.length());
            TokenDto tokenDto = RedisUtils.get(tokenKey);
            return tokenDto != null ? tokenDto.getIp() : null;
        }
        return null;
    }

}
