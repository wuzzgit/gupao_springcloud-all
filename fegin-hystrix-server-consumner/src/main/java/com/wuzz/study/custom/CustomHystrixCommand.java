package com.wuzz.study.custom;

import java.lang.annotation.*;

/**
 * 自定义hystrix注解实现
 * @author wuzongzhao
 * @date 2020/12/14 16:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomHystrixCommand {

    /**
     * 默认超时时间
     * @return
     */
    int timeout() default 1000;

    /**
     * 回退方法
     * @return
     */
    String fallback() default "";
}
