package org.jayjay.air.security.service;

import org.jayjay.air.security.entity.SysPermission;
import org.jayjay.air.security.entity.SysRole;
import org.jayjay.air.security.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return
     */
    List<SysRole> findRoleByUserId(String userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    List<SysPermission> findAuthByUserId(String userId);
}
