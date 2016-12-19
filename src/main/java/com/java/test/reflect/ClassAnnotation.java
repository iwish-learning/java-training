package com.java.test.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this annotation's modifier is default.
 * @author Danny.Wang
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
abstract @interface ClassAnnotation {

}
