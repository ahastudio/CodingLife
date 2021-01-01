package com.ahastudio.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Award {
    @AliasFor("prize")
    String[] value() default {};

    @AliasFor("value")
    String[] prize() default {};
}
