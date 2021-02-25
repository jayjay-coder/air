package org.jayjay.air.security.controller;

import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.security.entity.SysUser;
import org.jayjay.air.security.entity.SysUserRole;
import org.jayjay.air.security.service.SysUserRoleService;
import org.jayjay.air.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: RegisterController
 * @Description: 注册用户实例Contrller
 */
@RestController
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 注册普通用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/user")
    public ResultModel user(String username, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPassword(bCryptPasswordEncoder.encode(password));
        sysUser.setStatus(0);
        sysUserService.save(sysUser);

        // 注册普通用户
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        sysUserRole.setRoleId("735ce39b111ca24ce46dae69973b8917");
        sysUserRoleService.save(sysUserRole);

        return ResultModel.success(sysUser);
    }
}
