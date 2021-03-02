package org.jayjay.air.common.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 父级
     */
    @TableField("parentId")
    private String parentId;

    /**
     * 图标class
     */
    private String menuIcon;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 顺序
     */
    private Integer sortNum;

    /**
     * 类型，0菜单，1按钮
     */
    private Integer type;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
