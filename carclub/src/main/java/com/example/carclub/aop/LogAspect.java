package com.example.carclub.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    // 定义一个切入点
    @Pointcut("execution(* com.example.carclub.service.*.*(..))")
    public void pc1(){

    }

    // 前置通知
    @Before(value = "pc1()")
    public void before(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("在切面上调用了前置通知，"+name + "方法开始执行...");
    }

    // 后置通知
    @After(value = "pc1()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("在切面上调用了后置通知，"+name + "方法执行结束...");
    }

    // 返回通知
    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String name = jp.getSignature().getName();
        System.out.println("在切面上调用了返回通知，"+name + "方法返回值为：" + result);
    }

    // 异常通知
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        String name = jp.getSignature().getName();
        System.out.println("在切面上调用了异常通知，"+name + "方法抛异常了，异常是：" + e.getMessage());
    }

    // 环绕通知
    @Around("pc1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();
        // 统计方法执行时间
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("在切面上调用了环绕通知，"+name + "方法执行时间为：" + (end - start)/1000 + " s");
        return result;
    }

}