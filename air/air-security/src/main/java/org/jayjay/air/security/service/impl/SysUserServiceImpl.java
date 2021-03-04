package org.jayjay.air.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jayjay.air.common.dto.UserDto;
import org.jayjay.air.common.entity.SysRole;
import org.jayjay.air.common.entity.SysUser;
import org.jayjay.air.common.entity.SysUserRole;
import org.jayjay.air.common.mapper.SysUserMapper;
import org.jayjay.air.common.util.CommonUtils;
import org.jayjay.air.security.service.SysRoleService;
import org.jayjay.air.security.service.SysUserRoleService;
import org.jayjay.air.security.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
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

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    @Override
    public SysUser findUserByUserName(String username) {
        return this.baseMapper.selectOne(
                new QueryWrapper<SysUser>().lambda().eq(SysUser::getUserName, username).ne(SysUser::getStatus, "0"));
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
        sysUser.setStatus(1);
        CommonUtils.setUpdateInfo(sysUser);
        this.save(sysUser);

        // 注册普通用户
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        SysRole nomal = sysRoleService.getSysRoleByCode("nomal");
        sysUserRole.setRoleId(nomal != null ? nomal.getId() : null);
        sysUserRoleService.save(sysUserRole);
        return sysUser;
    }

    /**
     * 注册
     *
     * @param sysUser
     * @return
     */
    @Override
    public UserDto register(SysUser sysUser) {
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        CommonUtils.setUpdateInfo(sysUser);
        this.save(sysUser);

        // 注册普通用户
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        SysRole nomal = sysRoleService.getSysRoleByCode("nomal");
        sysUserRole.setRoleId(nomal != null ? nomal.getId() : null);
        sysUserRoleService.save(sysUserRole);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(sysUser, userDto);
        return userDto;
    }


}
