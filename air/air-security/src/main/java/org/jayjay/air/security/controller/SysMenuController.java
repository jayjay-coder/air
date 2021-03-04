package org.jayjay.air.security.controller;


import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.entity.SysMenu;
import org.jayjay.air.security.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("getMenus")
    public ResultModel getMenuList(){
        List<SysMenu> menuList = sysMenuService.findMenuByCurrUser();
        return ResultModel.success(menuList);
    }
}

