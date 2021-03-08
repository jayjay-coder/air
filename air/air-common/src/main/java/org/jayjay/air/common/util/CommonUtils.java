package org.jayjay.air.common.util;

import org.apache.commons.lang3.StringUtils;
import org.jayjay.air.common.config.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author: JayJay
 * @Date: 1/3/2021
 * @ClassName: CommonUtils
 * @Description: 通用工具类
 */
public class CommonUtils {


    public static void setUpdateInfo(Object obj) {
        SysUserDetails sysUserDetails = getCurrentUser();
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            try {
                String id = null;
                Field idField = cls.getDeclaredField("id");
                if (idField != null) {
                    idField.setAccessible(true);
                    id = (String) idField.get(obj);
                }
                for (Field field : fields) {
                    field.setAccessible(true);
                    if ("createBy".equals(field.getName()) && StringUtils.isBlank(id)) {
                        field.set(obj, sysUserDetails.getId());
                    }
                    if ("createTime".equals(field.getName()) && StringUtils.isBlank(id)) {
                        field.set(obj, LocalDateTime.now());
                    }
                    if ("updateBy".equals(field.getName())) {
                        field.set(obj, sysUserDetails.getId());
                    }
                    if ("updateTime".equals(field.getName())) {
                        field.set(obj, LocalDateTime.now());
                    }
                }

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

    }


    public static SysUserDetails getCurrentUser() {
        SysUserDetails sysUserDetails = null;
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticated.getName();
        if ("Guest".equals(name)) {
            sysUserDetails = new SysUserDetails();
            sysUserDetails.setId("Guest");
            sysUserDetails.setUserName("Guest");
            return sysUserDetails;
        }
        sysUserDetails = (SysUserDetails) authenticated.getPrincipal();
        return sysUserDetails;
    }


    /**
     * URl匹配，可模糊匹配
     *
     * @param patternUrl
     * @param requestUrl
     * @return
     */
    public static boolean match(String patternUrl, String requestUrl) {
        if (StringUtils.isEmpty(patternUrl) || StringUtils.isEmpty(requestUrl)) {
            return false;
        }
        PathMatcher matcher = new AntPathMatcher();
        return matcher.match(patternUrl, requestUrl);
    }

    public static void main(String[] args) {
        System.out.println(match("/**/login/**","/login"));
        System.out.println(match("/**/login","/login"));
        System.out.println(match("/login/**","/user/login"));
        System.out.println(match("**/user/login","/user/login"));
        System.out.println(match("/user/login/**","/user/login"));
    }

}
