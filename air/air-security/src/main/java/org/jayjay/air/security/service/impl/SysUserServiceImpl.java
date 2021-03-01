package org.jayjay.air.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jayjay.air.security.entity.SysPermission;
import org.jayjay.air.security.entity.SysRole;
import org.jayjay.air.security.entity.SysUser;
import org.jayjay.air.security.mapper.SysUserMapper;
import org.jayjay.air.security.service.SysUserService;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    @Override
    public SysUser findUserByUserName(String username) {
        return this.baseMapper.selectOne(
                new QueryWrapper<SysUser>().lambda().eq(SysUser::getUserName, username).ne(SysUser::getStatus, "1"));
    }




}
