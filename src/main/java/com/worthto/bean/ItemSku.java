package com.worthto.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ItemSku {
    private Long id;

    @NotNull(message = "itemId不能为空")
    private Long itemId;

    @NotBlank(message = "color不能为空")
    private String color;

    @NotBlank(message = "itemSize不能为空")
    private String itemSize;

    @NotNull(message = "价格不能为空")
    private Integer price;

    @NotNull(message = "初始库存不能为空")
    private Long stock;//库存数量

    @NotNull(message = "userId不能为空")
    private Long userId;

    @NotNull(message = "创建时间不能为空")
    private Date createTime;

    @NotNull(message = "库存总量不能为空")
    private Long totalStock;//历史库存总量

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Long totalStock) {
        this.totalStock = totalStock;
    }

}