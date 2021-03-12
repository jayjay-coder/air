package org.jayjay.air.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: JayJay
 * @Date: 11/3/2021
 * @ClassName: TokenDto
 * @Description:
 */
@Data
public class TokenDto {

    private String token;

    private String username;

    private String ip;

    private LocalDateTime createTime;

    private LocalDateTime expirationTime;
}
