package com.worthto.bean.service.enums;

/**
 * Created by gezz on 2017/5/1.
 */
public enum ItemExitOrderStatusEnum {
    S_NEW(1, "新建"),
    S_EXITED(1, "已出库"),
    ;

    private Integer value;
    private String text;

    ItemExitOrderStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public static ItemExitOrderStatusEnum getEnumByInt(Integer value) {
        for (ItemExitOrderStatusEnum _enum : ItemExitOrderStatusEnum.values()) {
            if (_enum.getValue() == value) {
                return _enum;
            }
        }
        throw new IllegalArgumentException("参数错误");
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
