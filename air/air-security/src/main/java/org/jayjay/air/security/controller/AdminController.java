package org.jayjay.air.security.controller;

import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.security.entity.SysPermission;
import org.jayjay.air.security.entity.SysRole;
import org.jayjay.air.security.entity.SysUser;
import org.jayjay.air.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: AdminController
 * @Description: 管理员Contrller
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private SysUserService sysUserSerivce;

    /**
     * 查询用户信息
     *
     * @return
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/list")
    public ResultModel list() {
        List<SysUser> userList = sysUserSerivce.list();
        return ResultModel.success(userList);
    }

    /**
     * 查询用户角色
     *
     * @return
     */
    @PreAuthorize(value = "hasRole('ADMIN') or hasPermission('/user/role', 'sys:role:info')")
    @RequestMapping(value = "/role")
    public ResultModel role(String id) {
        List<SysRole> roleList = sysUserSerivce.findRoleByUserId(id);
        return ResultModel.success(roleList);
    }

    /**
     * 查询用户权限
     *
     * @return
     */
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'USER') and hasPermission('/user/auth', 'sys:auth:info')")
    @RequestMapping(value = "/auth")
    public ResultModel auth(String id) {
        List<SysPermission> authList = sysUserSerivce.findAuthByUserId(id);
        return ResultModel.success(authList);
    }

}
