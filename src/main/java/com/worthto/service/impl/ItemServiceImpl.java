package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.Item;
import com.worthto.bean.service.ItemQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.ItemDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;


    @Override
    public int editItem(Item item) {
        ValidateUtils.validate(item);
        item.setName(StringUtils.upperCase(item.getName()));
        if (item.getId() == null) {
            // --新增--
            //查重
            Item query = new Item();
            query.setName(item.getName());
            if (itemDao.countByQuery(query) > 0) {
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemDao.insert(item);
        } else {
            // --更新--
            ItemQuery query = new ItemQuery();
            query.setName(item.getName());
            Item dbItem = itemDao.selectOneByQuery(query);
            if (dbItem != null && dbItem.getId() != item.getId()) {
                //其他尺码有同名
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为",query.getName(),"的尺码"));
            }
            return itemDao.updateByPrimaryKeySelective(item);
        }
    }

    @Override
    public PageBean itemPageList(ItemQuery itemQuery) {
        itemQuery.setSortBy(new SortBy("name", SortBy.DirectionEnum.ASC));
        int itemCount = itemDao.countByQuery(itemQuery);
        PageBean<Item> itemPageBean = new PageBean<>(itemCount,itemQuery);
        List<Item> itemList = itemDao.selectByItemQuery(itemQuery);
        itemPageBean.setList(itemList);
        return itemPageBean;
    }

    @Override
    public Item findById(Long itemId) {
        if (itemId == null) {
            throw new ParamException("itemId不能为空");
        }
        return itemDao.selectByPrimaryKey(itemId);
    }
}
