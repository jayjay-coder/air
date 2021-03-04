package org.jayjay.air.security.service;

import org.jayjay.air.common.entity.SysMenu;
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
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户id查询菜单
     * @param userId
     * @return
     */
    List<SysMenu> findMenuByUserId(String userId);

    /**
     * 查询当前用户菜单
     * @param
     * @return
     */
    List<SysMenu> findMenuByCurrUser();
}
