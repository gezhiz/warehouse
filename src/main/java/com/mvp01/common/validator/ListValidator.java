package com.mvp01.common.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Created by wenjie on 16/1/8.
 */
public class ListValidator implements ConstraintValidator<ListEle, List> {
    private ListEle listEle;
    @Override
    public void initialize(ListEle listEle) {
        this.listEle = listEle;
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if (null == list) {
            return false;
        }
        if (!listEle.allowEmpty() && list.isEmpty()) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            String errmsg = ValidateUtils.validateRetStr(o);
            if (!StringUtils.isBlank(errmsg)) {
                return false;
            }
        }
        return true;
    }
}
