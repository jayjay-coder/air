package org.jayjay.air.common.mapper;

import org.jayjay.air.common.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户id查询菜单
     * @param userId
     * @return
     */
    List<SysMenu> findMenuByUserId(String userId);

}
