package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.ItemSize;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.ItemSizeDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemSizeService;
import org.apache.commons.lang.StringUtils;
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
    public int editItemSize(ItemSize itemSize) {
        ValidateUtils.validate(itemSize);
        itemSize.setName(StringUtils.upperCase(itemSize.getName()));
        if (itemSize.getId() == null) {
            // --新增--
            //查重
            ItemSize query = new ItemSize();
            query.setName(itemSize.getName());
            if (itemSizeDao.countByQuery(query) > 0) {
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemSizeDao.insert(itemSize);
        } else {
            // --更新--
            ItemSizeQuery query = new ItemSizeQuery();
            query.setName(itemSize.getName());
            ItemSize dbItemSize = itemSizeDao.selectOneByQuery(query);
            if (dbItemSize != null && dbItemSize.getId() != itemSize.getId()) {
                //其他尺码有同名
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemSizeDao.updateByPrimaryKeySelective(itemSize);
        }
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

    @Override
    public ItemSize findById(Long itemSizeId) {
        if (itemSizeId == null) {
            throw new ParamException("itemSizeId不能为空");
        }
        return itemSizeDao.selectByPrimaryKey(itemSizeId);
    }
}
