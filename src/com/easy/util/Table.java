package com.easy.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Table
 * @Description TODO
 * @Author MLY
 * @Date 2024/6/11 9:58
 * @Version 1.0
 */

@Target(ElementType.TYPE)//注解的目标类型，这里是类
@Retention(RetentionPolicy.RUNTIME)//注解的生命周期，在运行时期间有效
public @interface Table {
    String tablename();
}
