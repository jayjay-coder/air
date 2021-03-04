package org.jayjay.air.security.controller;


import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.common.dto.UserDto;
import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.entity.SysPermission;
import org.jayjay.air.common.entity.SysRole;
import org.jayjay.air.common.entity.SysUser;
import org.jayjay.air.security.service.SysPermissionService;
import org.jayjay.air.security.service.SysRoleService;
import org.jayjay.air.security.service.SysUserRoleService;
import org.jayjay.air.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/user")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;


    @Autowired
    private SysPermissionService sysPermissionService;


    /**
     * 注册普通用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping(value = "/register")
    public ResultModel addUser(String username, String password) {
        SysUser sysUser = sysUserService.register(username, password);
        return ResultModel.success(sysUser);
    }

    /**
     * 注册普通用户
     *
     * @param sysUser 用户
     * @return
     */
    @PostMapping(value = "/register")
    public ResultModel register(@RequestBody SysUser sysUser) {
        UserDto userDto = sysUserService.register(sysUser);
        return ResultModel.success(userDto);
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @PreAuthorize(value = "hasPermission('/user/info', 'sys:user:info')")
    @GetMapping(value = "/info")
    public ResultModel info() {
        SysUserDetails sysUserDetails = (SysUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        SysUser sysUser = sysUserService.getById(sysUserDetails.getId());
        return ResultModel.success(sysUser);
    }



    /**
     * 查询用户信息
     *
     * @return
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/list")
    public ResultModel list() {
        List<SysUser> userList = sysUserService.list();
        return ResultModel.success(userList);
    }

    /**
     * 查询用户角色
     *
     * @return
     */
    @PreAuthorize(value = "hasRole('ADMIN') or hasPermission('/user/role', 'sys:role:info')")
    @GetMapping(value = "/role")
    public ResultModel role(String id) {
        List<SysRole> roleList = sysRoleService.findRoleByUserId(id);
        return ResultModel.success(roleList);
    }

    /**
     * 查询用户权限
     *
     * @return
     */
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'USER') and hasPermission('/user/auth', 'sys:auth:info')")
    @GetMapping(value = "/auth")
    public ResultModel auth(String id) {
        List<SysPermission> authList = sysPermissionService.findPermsByUserId(id);
        return ResultModel.success(authList);
    }
}

