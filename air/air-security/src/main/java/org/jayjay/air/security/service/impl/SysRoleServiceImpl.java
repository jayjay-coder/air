package org.jayjay.air.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jayjay.air.common.entity.SysRole;
import org.jayjay.air.common.mapper.SysRoleMapper;
import org.jayjay.air.security.service.SysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<SysRole> findRoleByUserId(String userId) {
        return this.baseMapper.findRoleByUserId(userId);
    }


    /**
     * 根据角色code查询角色
     *
     * @param code
     * @return
     */
    @Override
    public SysRole getSysRoleByCode(String code) {
        SysRole sysRole = this.baseMapper.selectOne(
                new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleCode, code));
        return sysRole;
    }

}
