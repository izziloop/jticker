package com.izziloop.jticker.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
public @interface Postpone {

    /** The postpone delay in milliseconds */
    int duration() default 10;

}
