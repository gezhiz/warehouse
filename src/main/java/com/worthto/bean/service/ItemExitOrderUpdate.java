package com.worthto.bean.service;

import javax.validation.constraints.NotNull;

/**
 * Created by gezz on 2017/4/22.
 */
public class ItemExitOrderUpdate {
    @NotNull(message = "的id不能为空")
    private Long id;//itemExitOrder的id
    @NotNull(message = "addStock不能为空")
    private Long addCount;

    private ItemExitOrderUpdate() {}

    public ItemExitOrderUpdate(Long id, Long addCount) {
        this.id = id;
        this.addCount = addCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddCount() {
        return addCount;
    }

    public void setAddCount(Long addCount) {
        this.addCount = addCount;
    }
}
