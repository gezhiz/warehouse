package com.worthto.bean;

import javax.validation.constraints.NotNull;

public class ItemSize {
    private Long id;
    @NotNull(message = "尺寸名称不能为空")
    private String name;
    @NotNull(message = "用户id不能为空不能为空")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}