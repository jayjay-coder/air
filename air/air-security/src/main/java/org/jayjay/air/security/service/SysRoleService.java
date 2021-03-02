package org.jayjay.air.security.service;

import org.jayjay.air.common.entity.SysRole;
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
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return
     */
    List<SysRole> findRoleByUserId(String userId);

    /**
     * 根据角色code查询角色
     * @param code
     * @return
     */
    SysRole getSysRoleByCode(String code);

}
