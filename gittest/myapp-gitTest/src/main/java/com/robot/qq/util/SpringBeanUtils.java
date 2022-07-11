package com.robot.qq.util;

import com.robot.qq.annotation.Controllable;
import org.apache.el.stream.Stream;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sunhao
 * @data 2022/7/11
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 根据注解获取所有bean
     * @return bean列表
     */
    public static List<Object> getBean(){
        Map<String, Object> beans = SpringBeanUtils.getApplicationContext().getBeansWithAnnotation(Controllable.class);
        // 获取所有的类
        return new ArrayList<>(beans.values());
    }

}
