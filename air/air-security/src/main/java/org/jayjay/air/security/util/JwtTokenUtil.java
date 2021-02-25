package org.jayjay.air.security.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jayjay.air.security.config.JwtConfig;
import org.jayjay.air.security.config.SysUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * @Author: JayJay
 * @Date: 24/2/2021
 * @ClassName: JWTTokenUtil
 * @Description: JWT生产Token工具类
 */
@Slf4j
public class JwtTokenUtil {
    /**
     * 创建Token
     *
     * @param sysUserDetails 用户信息
     * @return
     */
    public static String createAccessToken(SysUserDetails sysUserDetails) {
        String token = Jwts.builder().setId(// 设置JWT
                sysUserDetails.getId().toString()) // 用户Id
                .setSubject(sysUserDetails.getUsername()) // 主题
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer("C3Stones") // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.expiration)) // 过期时间
                .signWith(SignatureAlgorithm.HS512, JwtConfig.secret) // 签名算法、密钥
                .claim("authorities", JSON.toJSONString(sysUserDetails.getAuthorities())).compact(); // 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
        return JwtConfig.tokenPrefix + token;
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
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return sysUserDetails;
    }

}
