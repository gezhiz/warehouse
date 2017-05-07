package com.worthto.bean.service;

import com.mvp01.common.bean.BaseBean;

/**
 * Created by gezz on 2017/5/7.
 */
public class ItemExitOrderDetail extends BaseBean {
    private String itemCategoryName;//商品类别
    private String itemName;//商品名称
    private String itemSize;//商品尺码
    private String color;//商品颜色
    private String skuCount;//sku的数量

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSkuCount() {
        return skuCount;
    }

    public void setSkuCount(String skuCount) {
        this.skuCount = skuCount;
    }
}
