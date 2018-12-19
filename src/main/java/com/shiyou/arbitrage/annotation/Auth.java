package com.shiyou.arbitrage.annotation;

import java.lang.annotation.*;

/**
 * FileName: Auth
 * Description: 在类或方法上添加@Auth就验证登录
 * Author:
 * Date:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
}
