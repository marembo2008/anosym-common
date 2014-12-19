package com.anosym.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * That the field is transient, and should not be included in the toString print out.
 * @author marembo
 */
@Retention(RUNTIME)
@Documented
@Target({FIELD})
public @interface StringTransient {
}
