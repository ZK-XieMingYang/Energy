package com.qhit.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class LogInterceptor {
    @Before(value = "execution(* com.qhit.*.controller.*.*(..))")
    public void before(JoinPoint jp){
        String classname = jp.getClass().getName();
        String metondname = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());
        System.out.println("开始执行类名："+classname+"下的方法："+metondname+",参数是："+args);
    }
    @AfterReturning(value = "execution(* com.qhit.*.controller.*.*(..))")
    public void afterrunning(JoinPoint jp){
        String classname = jp.getClass().getName();
        String metondname = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());
        System.out.println("结束执行类名："+classname+"下的方法："+metondname+",参数是："+args);
    }
}
