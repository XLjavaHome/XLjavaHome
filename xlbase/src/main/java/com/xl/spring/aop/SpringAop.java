package com.xl.spring.aop;

import com.xl.util.MethodUtil;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.aop跟方法的修饰符没有关系
 *
 * @author 徐立
 * @Date: 2018-01-16
 * @Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
@Log4j
@Component
@Aspect
public class SpringAop {
    /**
     * 返回值 包名 第二个*号：表示类名，*号表示所有的类 、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(* com.xl.service.impl.*.*(..))")
    private void anyMethod() {
        System.out.println("anyMethod" + MethodUtil.getMethodName());
    }

    @Before("anyMethod() && args(name)")
    private void doAccessCheck(String name) {
        System.out.println("传进来的参数" + name);
        System.out.println("前置通知");
    }

    @AfterReturning("anyMethod()")
    private void doAfter() {
        System.out.println("后置通知");
    }

    @After("anyMethod()")
    private void after() {
        System.out.println("最终通知");
    }

    /**
     * 方法抛出异常会执行该方法
     */
    @AfterThrowing("anyMethod()")
    private void doAfterThrow() {
        System.out.println("例外通知");
    }

    @Around("anyMethod()")
    private Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入环绕通知");
        //执行该方法
        Object object = pjp.proceed();
        System.out.println("退出方法");
        return object;
    }
}
