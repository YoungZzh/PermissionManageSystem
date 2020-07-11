package com.debug.pmp.server.annotation;

import java.lang.annotation.*;

/**
 * Author:Young
 * Date:2020/7/9-14:52
 */
//@Target:注解的作用目标 @Target(ElementType.METHOD)——方法
@Target(ElementType.METHOD)
//@Retention：注解的保留位置 RetentionPolicy.RUNTIME:这种类型的
// Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码
// 所读取和使用。
@Retention(RetentionPolicy.RUNTIME)
//@Document：说明该注解将被包含在javadoc中
@Documented
public @interface LogAnnotation {
    String value() default  "";
}
