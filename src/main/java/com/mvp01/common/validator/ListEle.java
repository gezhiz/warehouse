package com.mvp01.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by wenjie on 16/1/8.
 */
@Constraint(validatedBy = ListValidator.class) //具体的实现
@Target( { java.lang.annotation.ElementType.METHOD,
        java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface ListEle {
    String message() default "list内部数据异常";
    boolean allowEmpty() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
