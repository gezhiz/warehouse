package com.worthto.bean.service;

import javax.validation.constraints.NotNull;

/**
 * Created by gezz on 2017/4/22.
 */
public class ItemSkuStockUpdate {
    @NotNull(message = "itemSku的id不能为空")
    private Long skuId;
    @NotNull(message = "addStock不能为空")
    private Long addStock;

    private ItemSkuStockUpdate() {}

    public ItemSkuStockUpdate(Long skuId, Long addStock) {
        this.skuId = skuId;
        this.addStock = addStock;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getAddStock() {
        return addStock;
    }

    public void setAddStock(Long addStock) {
        this.addStock = addStock;
    }
}
