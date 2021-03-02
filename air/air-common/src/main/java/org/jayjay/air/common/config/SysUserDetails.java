package org.jayjay.air.common.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jayjay.air.common.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: JayJay
 * @Date: 24/2/2021
 * @ClassName: SysUserDetails
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDetails extends SysUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;

    /**
     * 账号是否过期
     */
    private boolean isAccountNonExpired = false;

    /**
     * 账号是否锁定
     */
    private boolean isAccountNonLocked = false;

    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = false;

    /**
     * 账号是否有效
     */
    private boolean isEnabled = true;

    /**
     * 获得用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }


    /**
     * 判断账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * 判断账号是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * 判断证书是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * 判断账号是否有效
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
