package com.worthto.dao.base;

import com.mvp01.common.exception.ParamException;
import java.util.List;

/**
 * Created by gezhizheng on 15/10/11
 */
public class PageBean<T> {
    //需要设置的参数
    private Integer pageSize = 10;//每页大小
    private Integer page = 1;//页码
    private List<T> list;

    //不需要设置的参数
    private int totalDataCount;//数据总条数
    private int totalPage;//总页数
    private int dataCount;//当前获取到得数据条数
    private boolean hasNext = false;//是否有下一页
    private boolean hasPrevious = false;//是否有前一页
    private Integer skip;

    public PageBean(int totalDataCount, PageQueryBean pageQueryBean) {
        pageSize = pageQueryBean.getPageSize();
        page = pageQueryBean.getPage();
        if (null == pageSize) {
            pageSize = 10;
        }

        if (null == page) {
            page = 1;
        }

        if (pageSize <= 0) {
            pageSize = 10;
        }

        if (totalDataCount >= 0) {
            this.totalDataCount = totalDataCount;
        }

        if (page <= 0) {
            page = 1;
        }
        this.totalPage = totalDataCount % pageSize == 0 ? totalDataCount / pageSize : totalDataCount / pageSize + 1;
        this.skip = (page - 1) * pageSize;

        pageQueryBean.setSkip(skip);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public int getDataCount() {
        return dataCount;
    }

    public List<T> getList() {
        if (list == null) {
            throw new ParamException("请设置数据列表参数：list");
        }
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
        this.dataCount = list.size();
        if (page > 1) {
            this.hasPrevious = true;
        }
        if (page < totalPage) {
            this.hasNext = true;
        }
        if (totalPage <= 1) {
            this.hasNext = false;
            this.hasPrevious = false;
        }
    }

    public Integer getPage() {
        return page;
    }

    public int getTotalDataCount() {
        return totalDataCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
