package com.coder.mvc.annotation;

import java.lang.annotation.*;

//注解作用范围--类
@Target({ElementType.TYPE})
//运行时可以通过反射机制获取
@Retention(RetentionPolicy.RUNTIME)
//javadoc生成文档时起作用  @Inherited（加了此注解接口表明接口可以被继承）
@Documented
public @interface EnjoyService {
    String value() default "";
}
