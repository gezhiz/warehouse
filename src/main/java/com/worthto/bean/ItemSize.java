package com.worthto.bean;

import javax.validation.constraints.NotNull;

public class ItemSize {
    private Long id;
    @NotNull(message = "尺寸名称不能为空")
    private String name;

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

}