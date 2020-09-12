package com.sama.springbootdemo01.practice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 * @author fjk
 * @date 2020年09月12日
 * @since jdk1.8
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 使用@Pointcut注解，来拦截UserService下的所有方法
     */
    @Pointcut("execution(* com.sama.springbootdemo01.practice.aop.UserTestService.*(..))")
    public void LogPointcut(){}


    /**
     * 环绕通知 @Around  ，也可以根据实际情况使用 @Before (前置通知)  @After (后置通知) 等
     * @Around 注解的方法的参数一定要是ProceedingJoinPoint 这个对象是JoinPoint的子类。我们可以把这个看作是切入点的那个方法的替身
     * ，这个proceedingJoinPoint有个proceed（）方法，相当于就是那切入点的那个方法执行，简单地说就是让目标方法执行
     * ，然后这个方法会返回一个对象，这个对象就是那个切入点所在位置的方法所返回的对象。
     *
     */
    @Around("LogPointcut()")
    public void aroundTest(ProceedingJoinPoint point) throws Throwable{
        try {
            String info = "保存日志";
            // point.getSignature().getName() 获取当前拦截方法名称
            if ("delete".equals(point.getSignature().getName())){
                info = "删除日志";
            }
            System.out.println("环绕通知 前置记录" + info);
            //point.proceed() 继续执行拦截方法
            point.proceed();
            System.out.println("环绕通知 后置记录" + info);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Before("LogPointcut()")
    public void beforeTest(){
        System.out.println("前置通知 记录日志");
    }

    @After("LogPointcut()")
    public void afterTest(){
        System.out.println("后置通知 记录日志");
    }


}
