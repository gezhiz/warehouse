package com.worthto.bean;

import com.mvp01.common.bean.BaseBean;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ItemExitOrder extends BaseBean{
    private Long id;

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "客户名称不能为空")
    private String clientName;

    private String clientAddress;

    private String clientContact;

    @NotBlank(message = "出库单标题不能为空")
    private String title;

    private Long itemCount;

    @NotNull(message = "入库单状态不能为空")
    private Integer status;//ItemExitOrderStatusEnum

    @NotNull(message = "创建时间不能为空")
    private Date createTime;

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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress == null ? null : clientAddress.trim();
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact == null ? null : clientContact.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}