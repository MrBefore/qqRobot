package com.robot.qq.util;

import org.springframework.core.io.ClassPathResource;

import java.util.Objects;

/**
 * 文件路径工具类
 *
 * @author wangzk
 */
public class PathUtils {
    /**
     * 获取文件绝对路径
     *
     * @param pathName String
     * @return String
     */
    public static String getPath(String pathName) {
        ClassPathResource classPathResource = new ClassPathResource(pathName);
        return Objects.requireNonNull(Objects.requireNonNull(classPathResource.getClassLoader()).getResource(pathName)).getPath();
    }
}
