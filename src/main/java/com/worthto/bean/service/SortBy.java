package com.worthto.bean.service;

/**
 * Created by gezz on 2017/4/15.
 */
public class SortBy {
    private String key;
    private DirectionEnum direction;

    private SortBy() {}

    public SortBy(String key, DirectionEnum direction) {
        this.key = key;
        this.direction = direction;
    }

    public enum DirectionEnum {
        DESC(1,"降序"),
        ASC(10,"升序");
        private Integer value;
        private String desc;

        DirectionEnum(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }
}
