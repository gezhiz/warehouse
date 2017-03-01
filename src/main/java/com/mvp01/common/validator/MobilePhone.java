package com.mvp01.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by wenjie on 16/1/8.
 */
@Constraint(validatedBy = MobilePhoneValidator.class) //具体的实现
@Target( { java.lang.annotation.ElementType.METHOD,
        java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface MobilePhone {
    String message() default "手机号码格式异常";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
