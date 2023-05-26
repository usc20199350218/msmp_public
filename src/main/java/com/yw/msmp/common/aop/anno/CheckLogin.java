package com.yw.msmp.common.aop.anno;

/**
 * 加上这个注解的方法 就要做前置增强
 * 进入方法之前 验证token
 */

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yanhaoyuwen
 */
@Retention( RUNTIME )
@Target( { FIELD, METHOD } )
public @interface CheckLogin {

}
