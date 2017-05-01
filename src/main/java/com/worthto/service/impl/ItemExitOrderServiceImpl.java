package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.ItemExitOrder;
import com.worthto.bean.service.ItemExitOrderQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.bean.service.enums.ItemExitOrderStatusEnum;
import com.worthto.dao.ItemExitOrderDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemExitOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("itemExitOrderService")
public class ItemExitOrderServiceImpl implements ItemExitOrderService {

    @Autowired
    private ItemExitOrderDao itemExitOrderDao;


    @Override
    public int editItemExitOrder(ItemExitOrder itemExitOrder) {
        itemExitOrder.setCreateTime(new Date());
        itemExitOrder.setStatus(ItemExitOrderStatusEnum.S_NEW.getValue());
        ValidateUtils.validate(itemExitOrder);
        if (itemExitOrder.getId() == null) {
            // --新增--
            itemExitOrder.setItemCount(0l);
            return itemExitOrderDao.insert(itemExitOrder);
        } else {
            // --更新--
            //查找是否存在
            ItemExitOrderQuery exitOrderQuery = new ItemExitOrderQuery();
            exitOrderQuery.setUserId(itemExitOrder.getUserId());
            exitOrderQuery.setId(itemExitOrder.getId());
            Integer count = itemExitOrderDao.countByQuery(exitOrderQuery);
            if (count == 0) {
                throw new ErrcodeException("出库单未找到");
            }
            return itemExitOrderDao.updateByPrimaryKeySelective(itemExitOrder);
        }
    }

    @Override
    public PageBean itemExitOrderPageList(ItemExitOrderQuery itemExitOrderQuery) {
        itemExitOrderQuery.setSortBy(new SortBy("create_time", SortBy.DirectionEnum.ASC));
        int itemCount = itemExitOrderDao.countByQuery(itemExitOrderQuery);
        PageBean<ItemExitOrder> itemExitOrderPageBean = new PageBean<>(itemCount,itemExitOrderQuery);
        List<ItemExitOrder> itemExitOrderList = itemExitOrderDao.selectByItemExitOrderQuery(itemExitOrderQuery);
        itemExitOrderPageBean.setList(itemExitOrderList);
        return itemExitOrderPageBean;
    }

    @Override
    public ItemExitOrder findById(Long itemExitOrderId) {
        if (itemExitOrderId == null) {
            throw new ParamException("itemExitOrderId不能为空");
        }
        return itemExitOrderDao.selectByPrimaryKey(itemExitOrderId);
    }
}
