package org.jayjay.air.security.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.jayjay.air.common.config.SysUserDetails;
import org.jayjay.air.common.entity.SysMenu;
import org.jayjay.air.common.mapper.SysMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jayjay.air.common.util.CommonUtils;
import org.jayjay.air.security.service.SysMenuService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    /**
     * 根据用户id查询菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> findMenuByUserId(String userId) {
        List<SysMenu> menuList = new ArrayList<SysMenu>();
        if(!StringUtils.isBlank(userId)) {
            List<SysMenu> rootMenus = baseMapper.findMenuByUserId(userId);
            // 最后的结果
            // 先找到所有的一级菜单
            List<SysMenu> parentMenus = rootMenus.stream().filter(menu -> StringUtils.isBlank(menu.getParentId())).collect(Collectors.toList());
            menuList.addAll(parentMenus);
            // 为一级菜单设置子菜单，getChild是递归调用的
            menuList.forEach(menu -> {
                menu.setChildMenus(getChild(menu.getId(), rootMenus));
            });
        }
        return menuList;
    }
    /**
     * 查询当前用户菜单
     *
     * @return
     */
    @Override
    public List<SysMenu> findMenuByCurrUser() {
        SysUserDetails currentUser = CommonUtils.getCurrentUser();
        List<SysMenu> menuList = this.findMenuByUserId(currentUser.getId());
        return menuList;
    }


    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootMenu
     *            要查找的列表
     * @return
     */
    private List<SysMenu> getChild(String id, List<SysMenu> rootMenu) {
        // 子菜单
        List<SysMenu> childList = new ArrayList<>();
        rootMenu.forEach(menu->{
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menu.getParentId())) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        });
        // 把子菜单的子菜单再循环一遍
        childList.forEach(menu->{
            // 没有url子菜单还有子菜单
            if (StringUtils.isBlank(menu.getMenuUrl())) {
                // 递归
                menu.setChildMenus(getChild(menu.getId(), rootMenu));
            }
        });
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
