package org.jayjay.air.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: JayJay
 * @Date: 4/3/2021
 * @ClassName: UserDto
 * @Description:
 */
@Data
public class UserDto {

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

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
}
