package com.xl.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/16
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "这是作者";

    String date();

    int revision() default 1;

    String comments();
}
