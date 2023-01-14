package com.khesam.logger.common.logging;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD })
@Retention(RUNTIME)
@Documented
public @interface LogMe {
    String incomeMessage() default "";
    String outcomeMessage() default "";
    String failureMessage() default "";
    boolean withMeta() default true;
    boolean withInput() default true;
    boolean withOutput() default true;
    boolean withException() default true;
}
