package com.easy.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName One
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/29 20:17
 * @Version 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface One {
    String columnName ();
    String sql();
}
