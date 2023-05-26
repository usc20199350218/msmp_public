package com.yw.msmp.common.aop.anno;

/**
 * 以后加上了这个注解的方法 需要验证是否具备执行这个方法的权限
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
public @interface CheckActionRignt {

    // 要执行方法必须拥有的权限吗
    String code( );

}
