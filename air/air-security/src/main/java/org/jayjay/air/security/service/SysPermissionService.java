package org.jayjay.air.security.service;

import org.jayjay.air.security.entity.SysPermission;
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
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    List<SysPermission> findPermsByUserId(String userId);
}
