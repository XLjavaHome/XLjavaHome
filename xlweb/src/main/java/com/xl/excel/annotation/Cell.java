package com.xl.excel.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 14-5-6.
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Cell {
    String title();
}
