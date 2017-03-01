package com.mvp01.common.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by wenjie on 16/1/8.
 */
public class MobilePhoneValidator implements ConstraintValidator<MobilePhone, String> {
    public static Pattern mobilePattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");

    @Override
    public void initialize(MobilePhone mobilePhone) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        return mobilePattern.matcher(s).matches();
    }
}
