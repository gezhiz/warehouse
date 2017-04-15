package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.utils.CommonUtil;
import com.worthto.bean.ItemSize;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.ItemSizeDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("itemSizeService")
public class ItemSizeServiceImpl implements ItemSizeService {

    @Autowired
    private ItemSizeDao itemSizeDao;


    @Override
    public int addItemSize(ItemSize itemSize) {
        //查重
        ItemSize query = new ItemSize();
        query.setName(itemSize.getName());
        query.setUserId(itemSize.getUserId());
        if (itemSizeDao.countByQuery(query) > 0) {
            throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
        }
        return itemSizeDao.insert(itemSize);
    }

    @Override
    public PageBean itemSizePageList(ItemSizeQuery itemSizeQuery) {
        itemSizeQuery.setSortBy(new SortBy("name", SortBy.DirectionEnum.ASC));
        int itemCount = itemSizeDao.countByQuery(itemSizeQuery);
        PageBean<ItemSize> itemSizePageBean = new PageBean<>(itemCount,itemSizeQuery);
        List<ItemSize> itemSizeList = itemSizeDao.selectByItemSizeQuery(itemSizeQuery);
        itemSizePageBean.setList(itemSizeList);
        return itemSizePageBean;
    }
}
