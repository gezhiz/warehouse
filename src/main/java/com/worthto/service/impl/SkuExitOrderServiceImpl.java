package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.Item;
import com.worthto.bean.ItemSku;
import com.worthto.bean.SkuExitOrder;
import com.worthto.bean.service.*;
import com.worthto.dao.ItemDao;
import com.worthto.dao.ItemExitOrderDao;
import com.worthto.dao.ItemSkuDao;
import com.worthto.dao.SkuExitOrderDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.SkuExitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("skuExitOrderService")
public class SkuExitOrderServiceImpl implements SkuExitOrderService {

    @Autowired
    private SkuExitOrderDao skuExitOrderDao;

    @Autowired
    private ItemSkuDao itemSkuDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemExitOrderDao itemExitOrderDao;


    @Override
    public int editSkuExitOrder(SkuExitOrder skuExitOrder) {
        skuExitOrder.setCreateTime(new Date());
        ItemSku dbItemSku = itemSkuDao.selectByPrimaryKey(skuExitOrder.getSkuId());
        if (dbItemSku == null) {
            throw new ParamException("商品sku未找到");
        }
        Item dbItem = itemDao.selectByPrimaryKey(dbItemSku.getItemId());
        if (dbItem == null) {
            throw new ParamException("商品未找到");
        }
        skuExitOrder.setItemId(dbItem.getId());

        ValidateUtils.validate(skuExitOrder);
        //如果存在id,则直接更新
        if (skuExitOrder.getId() != null) {
            //更新
            SkuExitOrder dbSkuExitOrder = skuExitOrderDao.selectByPrimaryKey(skuExitOrder.getId());
            return updateSkuExitOrder(skuExitOrder, dbSkuExitOrder, dbItem, dbItemSku);
        } else {
            //如果不存在id,则根据skuId查找是否存在
            //根据itemExitOrderId和skuId查找是否已经存在
            SkuExitOrder dbSkuExitOrder = findBySkuId(skuExitOrder);

            if (dbSkuExitOrder == null) {
                //新增
                return addSkuExitOrder(skuExitOrder, dbItem, dbItemSku);
            } else {
                return updateSkuExitOrder(skuExitOrder, dbSkuExitOrder, dbItem, dbItemSku);
            }
        }

    }

    /**
     * 查找当前出库单的某个商品sku出库单
     * @param skuExitOrder
     * @return
     */
    private SkuExitOrder findBySkuId(SkuExitOrder skuExitOrder) {
        SkuExitOrderQuery skuExitOrderQuery = new SkuExitOrderQuery();
        skuExitOrderQuery.setSkuId(skuExitOrder.getSkuId());
        skuExitOrderQuery.setUserId(skuExitOrder.getUserId());
        skuExitOrderQuery.setItemExitOrderId(skuExitOrder.getItemExitOrderId());
        return skuExitOrderDao.selectOneByQuery(skuExitOrderQuery);
    }

    /**
     * 更新SkuExitOrder
     * @param skuExitOrder
     * @param dbSkuExitOrder
     * @return
     */
    private int updateSkuExitOrder(SkuExitOrder skuExitOrder, SkuExitOrder dbSkuExitOrder, Item dbItem, ItemSku itemSku) {
        // --更新--
        //查找是否存在
        if (dbSkuExitOrder == null) {
            throw new ErrcodeException("sku出库单未找到");
        }
        if (dbSkuExitOrder.getId() == null) {
            throw new ParamException("更新SkuExitOrder--id为空");
        }
        skuExitOrder.setId(dbSkuExitOrder.getId());
        Long addCount = dbSkuExitOrder.getSkuCount()-skuExitOrder.getSkuCount();//库存变化
        updateStocks(skuExitOrder, dbItem, itemSku, addCount);


        return skuExitOrderDao.updateByPrimaryKeySelective(skuExitOrder);
    }

    /**
     * itemSku出库后更新数据库的各种库存数:item的库存，itemSku的库存，itemExitOrder的商品总数
     * @param skuExitOrder
     * @param dbItem
     * @param itemSku
     * @param addCount
     */
    private void updateStocks(SkuExitOrder skuExitOrder, Item dbItem, ItemSku itemSku, Long addCount) {
        //编辑ItemSku的库存
        ItemSkuStockUpdate skuStockUpdate = new ItemSkuStockUpdate(skuExitOrder.getSkuId(), addCount);
        itemSkuDao.updateStockById(skuStockUpdate, itemSku);
        //编辑Item的库存
        ItemCountUpdate itemSkuStockUpdate = new ItemCountUpdate(skuExitOrder.getItemId(),addCount);
        itemDao.updateStockById(itemSkuStockUpdate,dbItem);
        //编辑ItemExitOrder的库存
        ItemExitOrderUpdate itemExitOrderUpdate = new ItemExitOrderUpdate(skuExitOrder.getItemExitOrderId(),-addCount);
        itemExitOrderDao.updateStockById(itemExitOrderUpdate);
    }

    /**
     * 新增SkuExitOrder
     * @param skuExitOrder
     * @return
     */
    private int addSkuExitOrder(SkuExitOrder skuExitOrder, Item dbItem, ItemSku dbItemSku) {
        // --新增--
        //减少ItemSku的库存
        updateStocks(skuExitOrder, dbItem, dbItemSku, -skuExitOrder.getSkuCount());
        return skuExitOrderDao.insert(skuExitOrder);
    }

    @Override
    public PageBean skuExitOrderPageList(SkuExitOrderQuery skuExitOrderQuery) {
        skuExitOrderQuery.setSortBy(new SortBy("item_id", SortBy.DirectionEnum.ASC));
        int skuCount = skuExitOrderDao.countByQuery(skuExitOrderQuery);
        PageBean<SkuExitOrder> skuExitOrderPageBean = new PageBean<>(skuCount,skuExitOrderQuery);
        List<SkuExitOrder> skuExitOrderList = skuExitOrderDao.selectBySkuExitOrderQuery(skuExitOrderQuery);
        skuExitOrderPageBean.setList(skuExitOrderList);
        return skuExitOrderPageBean;
    }

    @Override
    public SkuExitOrder findById(Long skuExitOrderId) {
        if (skuExitOrderId == null) {
            throw new ParamException("skuExitOrderId不能为空");
        }
        return skuExitOrderDao.selectByPrimaryKey(skuExitOrderId);
    }
}
