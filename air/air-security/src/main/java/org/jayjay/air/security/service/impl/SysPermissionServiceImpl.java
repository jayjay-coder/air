package org.jayjay.air.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jayjay.air.security.entity.SysPermission;
import org.jayjay.air.security.mapper.SysPermissionMapper;
import org.jayjay.air.security.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<SysPermission> findPermsByUserId(String userId) {
        return this.baseMapper.findPermsByUserId(userId);
    }
}
