package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.ItemCategory;
import com.worthto.bean.service.ItemCategoryQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.ItemCategoryDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("itemCategoryService")
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryDao itemCategoryDao;


    @Override
    public int editItemCategory(ItemCategory itemCategory) {
        ValidateUtils.validate(itemCategory);
        itemCategory.setName(StringUtils.upperCase(itemCategory.getName()));
        if (itemCategory.getId() == null) {
            // --新增--
            //查重
            ItemCategory query = new ItemCategory();
            query.setName(itemCategory.getName());
            if (itemCategoryDao.countByQuery(query) > 0) {
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemCategoryDao.insert(itemCategory);
        } else {
            // --更新--
            ItemCategoryQuery query = new ItemCategoryQuery();
            query.setName(itemCategory.getName());
            ItemCategory dbItemCategory = itemCategoryDao.selectOneByQuery(query);
            if (dbItemCategory != null && dbItemCategory.getId() != itemCategory.getId()) {
                //其他尺码有同名
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemCategoryDao.updateByPrimaryKey(itemCategory);
        }
    }

    @Override
    public PageBean itemCategoryPageList(ItemCategoryQuery itemCategoryQuery) {
        itemCategoryQuery.setSortBy(new SortBy("name", SortBy.DirectionEnum.ASC));
        int itemCount = itemCategoryDao.countByQuery(itemCategoryQuery);
        PageBean<ItemCategory> itemCategoryPageBean = new PageBean<>(itemCount,itemCategoryQuery);
        List<ItemCategory> itemCategoryList = itemCategoryDao.selectByItemCategoryQuery(itemCategoryQuery);
        itemCategoryPageBean.setList(itemCategoryList);
        return itemCategoryPageBean;
    }

    @Override
    public ItemCategory findById(Long itemCategoryId) {
        if (itemCategoryId == null) {
            throw new ParamException("itemCategoryId不能为空");
        }
        return itemCategoryDao.selectByPrimaryKey(itemCategoryId);
    }
}
