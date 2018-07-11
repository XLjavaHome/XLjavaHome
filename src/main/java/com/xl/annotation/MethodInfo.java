package com.xl.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
