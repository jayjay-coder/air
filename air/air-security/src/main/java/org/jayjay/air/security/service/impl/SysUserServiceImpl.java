package org.jayjay.air.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jayjay.air.common.entity.SysUser;
import org.jayjay.air.common.entity.SysUserRole;
import org.jayjay.air.common.mapper.SysUserMapper;
import org.jayjay.air.security.service.SysUserRoleService;
import org.jayjay.air.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    

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

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public SysUser register(String username, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPassword(bCryptPasswordEncoder.encode(password));
        sysUser.setStatus(0);
        this.save(sysUser);

        // 注册普通用户
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        sysUserRole.setRoleId("735ce39b111ca24ce46dae69973b8917");
        sysUserRoleService.save(sysUserRole);
        return sysUser;
    }


}
