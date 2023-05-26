package com.yw.msmp.common.aop.anno;

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
public @interface CheckEfficient {
}

