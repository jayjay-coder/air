package org.jayjay.air.security.controller;

import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.security.config.SysUserDetails;
import org.jayjay.air.security.entity.SysUser;
import org.jayjay.air.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: UserController
 * @Description: 普通用户Contrller
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private SysUserService sysUserSerivce;

    /**
     * 查询用户信息
     *
     * @return
     */
    @PreAuthorize(value = "hasPermission('/user/info', 'sys:user:info')")
    @RequestMapping(value = "/info")
    public ResultModel info() {
        SysUserDetails sysUserDetails = (SysUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        SysUser sysUser = sysUserSerivce.getById(sysUserDetails.getId());
        return ResultModel.success(sysUser);
    }
}
