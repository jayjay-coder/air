package org.jayjay.air.web;

import org.jayjay.air.common.util.RedisUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: JayJay
 * @Date: 24/2/2021
 * @ClassName: AirApplication
 * @Description:
 */

@SpringBootApplication
@ComponentScan("org.jayjay.air")
@MapperScan(basePackages = {"org.jayjay.air.**.mapper"})
public class AirApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirApplication.class, args);
    }
}
