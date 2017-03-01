package com.mvp01.common.validator;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ParamException;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wenjie on 16/1/7.
 */
public class ValidateUtils {
    public interface ParamExceptionFactory {
        public ParamException geneParamException(String errmsg);
    }
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    private static final ParamExceptionFactory DEFAULT_PARAM_EXCEPTION_FACTORY = new ParamExceptionFactory() {
        public ParamException geneParamException(String errmsg) {
            return new ParamException(errmsg);
        }
    };

    public static String validateWithReturn(Object obj) {
        return validateInternal(obj);
    }

    public static void validate(Object obj) {
        validate(obj, null);
    }

    public static void validate(Object obj, int errcode) {
        validate(obj, errcode, null);
    }

    public static void validate(Object obj, ParamExceptionFactory factory) {
        validate(obj, ResultBean.COMMON_ERROR_CODE, factory);
    }

    public static void validate(Object obj, int errcode, ParamExceptionFactory factory) {
        String errmsg = validateInternal(obj);
        if (!StringUtils.isBlank(errmsg)) {
            if (factory == null) {
                factory = DEFAULT_PARAM_EXCEPTION_FACTORY;
            }
            throw factory.geneParamException(errmsg);
        }
    }

    public static String validateRetStr(Object obj) {
        String errmsg = validateInternal(obj);
        if (!StringUtils.isBlank(errmsg)) {
            return errmsg;
        }
        return "";
    }

    private static String validateInternal(Object obj) {
//        StringBuffer buffer = new StringBuffer(64);//用于存储验证后的错误信息

        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR
                .validate(obj);//验证某个对象,，其实也可以只验证其中的某一个属性的

        Iterator<ConstraintViolation<Object>> iter = constraintViolations
                .iterator();
        while (iter.hasNext()) {
            String message = iter.next().getMessage();
            return message;
//            buffer.append(message);
        }
        return "";
//        return buffer.toString();
    }
}
