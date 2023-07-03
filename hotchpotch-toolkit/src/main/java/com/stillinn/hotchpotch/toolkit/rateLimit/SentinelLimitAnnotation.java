package com.stillinn.hotchpotch.toolkit.rateLimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author stillinn
 * Created on 2023-07-02
 */

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SentinelLimitAnnotation {
    String resourceName();
    int limitCount() default 5;
}
