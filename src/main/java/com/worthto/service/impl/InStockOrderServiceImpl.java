package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.InStockOrder;
import com.worthto.bean.ItemSku;
import com.worthto.bean.service.InStockOrderQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.InStockOrderDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.InStockOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("inStockOrderService")
public class InStockOrderServiceImpl implements InStockOrderService {

    @Autowired
    private InStockOrderDao inStockOrderDao;

    public int addInStockOrder(InStockOrder inStockOrder) {
        //仅添加
        ValidateUtils.validate(inStockOrder);
        return inStockOrderDao.insert(inStockOrder);
    }

    @Override
    public PageBean inStockOrderPageList(InStockOrderQuery inStockOrderQuery) {
        inStockOrderQuery.setSortBy(new SortBy("itemId", SortBy.DirectionEnum.ASC));
        int itemCount = inStockOrderDao.countByQuery(inStockOrderQuery);
        PageBean<InStockOrder> inStockOrderPageBean = new PageBean<>(itemCount,inStockOrderQuery);
        List<InStockOrder> inStockOrderList = inStockOrderDao.selectByInStockOrderQuery(inStockOrderQuery);
        inStockOrderPageBean.setList(inStockOrderList);
        return inStockOrderPageBean;
    }

    @Override
    public InStockOrder findById(Long inStockOrderId) {
        if (inStockOrderId == null) {
            throw new ParamException("inStockOrderId不能为空");
        }
        return inStockOrderDao.selectByPrimaryKey(inStockOrderId);
    }

    @Override
    public void inStock(ItemSku itemSku) {
        InStockOrder inStockOrder = new InStockOrder();
        BeanUtils.copyProperties(itemSku,inStockOrder);
        inStockOrder.setId(null);
        inStockOrder.setInStockCount(itemSku.getStock());
        inStockOrder.setUserId(itemSku.getUserId());
        inStockOrder.setSkuId(itemSku.getId());
        ValidateUtils.validate(inStockOrder);
        addInStockOrder(inStockOrder);
    }
}
