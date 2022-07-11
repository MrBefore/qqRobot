package com.robot.qq.aspect;

import com.robot.qq.annotation.Controllable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


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
    @Pointcut("execution(public * com.robot.qq.controller..*(com.robot.qq.entity.CallbackMsg, javax.servlet.http.HttpServletRequest))")
    private void management(){
    }

    /**
     * 判断是否阻止其方法执行，方法上注解的优先级大于类上注解的优先级
     * @param joinPoint joinPoint
     * @return 执行结果
     * @throws Throwable 异常
     */
    @Around(value = "management()", argNames = "joinPoint")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();

        // 若方法上有该注解且设置为禁用则禁用，设置为启用则执行原方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (signature.getMethod().isAnnotationPresent(Controllable.class)){
            Controllable methodAnnotation = signature.getMethod().getAnnotation(Controllable.class);
            if (methodAnnotation.value() == 0){
                return null;
            } else {
                return joinPoint.proceed(args);
            }
        }

        // 若类上有该注解且设置为禁用则禁用，设置为启用则执行原方法
        if (target.getClass().isAnnotationPresent(Controllable.class)){
            Controllable classAnnotation = target.getClass().getAnnotation(Controllable.class);
            if (classAnnotation.value() == 0){
                return null;
            } else {
                return joinPoint.proceed(args);
            }
        }
        // 若类和方法上均未设置该注解则执行原方法
        return joinPoint.proceed(args);
    }
}
