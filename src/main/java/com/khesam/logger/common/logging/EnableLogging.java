package com.khesam.logger.common.logging;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, METHOD })
@Retention(RUNTIME)
@Documented
@InterceptorBinding
public @interface EnableLogging {

    Layer value();

    @Nonbinding String profile() default "";

    enum Layer {
        CONTROLLER, SERVICE, REPOSITORY, UTILITY
    }
}
