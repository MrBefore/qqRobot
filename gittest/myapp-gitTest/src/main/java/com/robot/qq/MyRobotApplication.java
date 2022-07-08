package com.robot.qq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author wangzk
 */
@ServletComponentScan(basePackages = "com.robot.qq.common")
@SpringBootApplication
public class MyRobotApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyRobotApplication.class, args);
    }
}
