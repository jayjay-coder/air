package org.jayjay.air.security.service;

import org.jayjay.air.common.dto.UserDto;
import org.jayjay.air.common.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    SysUser findUserByUserName(String username);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    SysUser register(String username,String password);


    /**
     * 注册
     * @param sysUser
     * @return
     */
    UserDto register(SysUser sysUser);


}
