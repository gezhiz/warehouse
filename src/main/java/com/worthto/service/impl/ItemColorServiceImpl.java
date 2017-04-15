package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.ItemColor;
import com.worthto.bean.service.ItemColorQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.ItemColorDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemColorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("itemColorService")
public class ItemColorServiceImpl implements ItemColorService {

    @Autowired
    private ItemColorDao itemColorDao;


    @Override
    public int editItemColor(ItemColor itemColor) {
        ValidateUtils.validate(itemColor);
        itemColor.setName(StringUtils.upperCase(itemColor.getName()));
        if (itemColor.getId() == null) {
            // --新增--
            //查重
            ItemColor query = new ItemColor();
            query.setName(itemColor.getName());
            if (itemColorDao.countByQuery(query) > 0) {
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemColorDao.insert(itemColor);
        } else {
            // --更新--
            ItemColorQuery query = new ItemColorQuery();
            query.setName(itemColor.getName());
            ItemColor dbItemColor = itemColorDao.selectOneByQuery(query);
            if (dbItemColor != null && dbItemColor.getId() != itemColor.getId()) {
                //其他尺码有同名
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemColorDao.updateByPrimaryKey(itemColor);
        }
    }

    @Override
    public PageBean itemColorPageList(ItemColorQuery itemColorQuery) {
        itemColorQuery.setSortBy(new SortBy("name", SortBy.DirectionEnum.ASC));
        int itemCount = itemColorDao.countByQuery(itemColorQuery);
        PageBean<ItemColor> itemColorPageBean = new PageBean<>(itemCount,itemColorQuery);
        List<ItemColor> itemColorList = itemColorDao.selectByItemColorQuery(itemColorQuery);
        itemColorPageBean.setList(itemColorList);
        return itemColorPageBean;
    }

    @Override
    public ItemColor findById(Long itemColorId) {
        if (itemColorId == null) {
            throw new ParamException("itemColorId不能为空");
        }
        return itemColorDao.selectByPrimaryKey(itemColorId);
    }
}
