package com.worthto.bean.service;

import javax.validation.constraints.NotNull;

/**
 * Created by gezz on 2017/4/22.
 */
public class ItemCountUpdate {
    @NotNull(message = "itemSku的id不能为空")
    private Long itemId;
    @NotNull(message = "addStock不能为空")
    private Long addCount;

    private ItemCountUpdate() {}

    public ItemCountUpdate(Long itemId, Long addCount) {
        this.itemId = itemId;
        this.addCount = addCount;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getAddCount() {
        return addCount;
    }

    public void setAddCount(Long addCount) {
        this.addCount = addCount;
    }
}
