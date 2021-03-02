package org.jayjay.air.security.service.impl;

import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.common.entity.SysRole;
import org.jayjay.air.common.entity.SysUser;
import org.jayjay.air.security.service.SysPermissionService;
import org.jayjay.air.security.service.SysRoleService;
import org.jayjay.air.security.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: SysUserDetailsService
 * @Description: 用户登录Service
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findUserByUserName(username);
        if (sysUser != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(sysUser, sysUserDetails);

            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合

            List<SysRole> roleList = sysRoleService.findRoleByUserId(sysUserDetails.getId());
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });

            sysUserDetails.setAuthorities(authorities);

            return sysUserDetails;
        }
        return null;
    }
}
