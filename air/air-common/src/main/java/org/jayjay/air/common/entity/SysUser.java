package org.jayjay.air.common.entity;

import java.time.LocalDateTime;
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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 失败时间
     */
    private LocalDateTime failTime;

    /**
     * 头像
     */
    private String headThumb;

    /**
     * 电话
     */
    private String phone;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登录次数
     */
    private Integer loginNumber;

    /**
     * 状态
     */
    private Integer status = 1;

    /**
     * 当前角色
     */
    private String currentRole;

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
