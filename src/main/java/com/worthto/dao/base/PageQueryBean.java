package com.worthto.dao.base;

/**
 * Created by gezz on 2017/3/18.
 */
public interface PageQueryBean {
    public Integer getSkip();

    public void setSkip(Integer skip);

    public Integer getPage();

    public void setPage(Integer page);

    public Integer getPageSize();

    public void setPageSize(Integer pageSize);
}
