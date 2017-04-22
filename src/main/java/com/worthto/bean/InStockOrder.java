package com.worthto.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class InStockOrder {
    private Long id;

    @NotNull(message = "userId不能为空")
    private Long userId;

    @NotNull(message = "itemId不能为空")
    private Long itemId;

    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @NotBlank(message = "color不能为空")
    private String color;

    @NotBlank(message = "itemSize不能为空")
    private String itemSize;

    @NotNull(message = "inStockCount不能为空")
    private Long inStockCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize == null ? null : itemSize.trim();
    }

    public Long getInStockCount() {
        return inStockCount;
    }

    public void setInStockCount(Long inStockCount) {
        this.inStockCount = inStockCount;
    }
}