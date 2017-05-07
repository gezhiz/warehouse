package com.worthto.bean;

import com.mvp01.common.bean.BaseBean;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SkuExitOrder extends BaseBean {
    private Long id;

    @NotNull(message = "itemId不能为空")
    private Long itemId;
    @NotNull(message = "skuId不能为空")
    private Long skuId;
    @NotNull(message = "skuCount不能为空")
    private Long skuCount;
    @NotNull(message = "createTime不能为空")
    private Date createTime;
    @NotNull(message = "itemExitOrderId不能为空")
    private Long itemExitOrderId;
    @NotNull(message = "userId不能为空")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSkuCount() {
        return skuCount;
    }

    public void setSkuCount(Long skuCount) {
        this.skuCount = skuCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getItemExitOrderId() {
        return itemExitOrderId;
    }

    public void setItemExitOrderId(Long itemExitOrderId) {
        this.itemExitOrderId = itemExitOrderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}