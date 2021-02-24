package org.jayjay.air.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: JayJay
 * @Date: 24/2/2021
 * @ClassName: AirApplication
 * @Description:
 */

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class AirApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirApplication.class, args);
    }
}
