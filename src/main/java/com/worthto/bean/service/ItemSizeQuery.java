package com.worthto.bean.service;

import com.worthto.bean.ItemSize;
import com.worthto.dao.base.PageQueryBean;

/**
 * Created by gezz on 2017/3/18.
 */
public class ItemSizeQuery extends ItemSize implements PageQueryBean {
    private Integer page;
    private Integer pageSize;
    private Integer skip;
    private SortBy sortBy;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }
}
