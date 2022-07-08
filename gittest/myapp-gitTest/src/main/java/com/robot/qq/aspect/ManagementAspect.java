package com.robot.qq.aspect;

import com.robot.qq.annotation.Controllable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author sunhao
 * @data 2022/7/8
 */
@Component
@Aspect
public class ManagementAspect {
    /**
     * 拦截controller中所有公用方法
     */
    @Pointcut("execution(public * com.robot.qq.controller..*(..))")
    private void management(){
    }

    @Around(value = "management()", argNames = "joinPoint")
    private Object before(ProceedingJoinPoint joinPoint){
        Object target = joinPoint.getTarget();
        // 判断类上是否具有该注解，如果该controller上具有该注解则拦截其方法执行
        if (target.getClass().isAnnotationPresent(Controllable.class)){
            return null;
        }
        return null;
    }
}
