package com.robot.qq.annotation;

import com.robot.qq.enums.TagEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来控制功能是否能够执行
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Controllable {
    // 标签
    TagEnum tag() default TagEnum.BASE_FUNCTION;
    // 状态 1启用，2禁用
    int value() default 1;
}
