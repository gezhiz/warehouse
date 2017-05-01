package com.mvp01.common.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenjie on 15/7/3.
 */
public class BaseBean implements Serializable{
    private Map<String, Object> extMap;

    public Map<String, Object> getExtMap() {
        return extMap;
    }

    /**
     * 返回查询字段值，注意不是线程安全
     * @param key
     */
    public Object getExtValue(String key) {
        return extMap != null ? extMap.get(key) : null;
    }

    /**
     * 设置查询字段值，注意不是线程安全
     * @param key
     * @param value
     */
    public void setExtValue(String key, Object value) {
        if (extMap == null) {
            extMap = new HashMap<String, Object>();
        }
        extMap.put(key, value);
    }

    /**
     * 设置查询字段值，注意不是线程安全
     * @param map
     */
    public void putAllExtValue(Map<String, Object> map) {
        if (map != null) {
            if (extMap == null) {
                extMap = new HashMap<String, Object>();
            }

            extMap.putAll(map);
        }
    }

    public void setExtMap(Map<String, Object> extMap) {
        this.extMap = extMap;
    }

}
