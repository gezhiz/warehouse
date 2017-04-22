package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.InStockOrder;
import com.worthto.bean.ItemSku;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.ItemSkuDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.InStockOrderService;
import com.worthto.service.ItemSkuService;
import com.worthto.utils.OrderUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("itemSkuService")
public class ItemSkuServiceImpl implements ItemSkuService {

    @Autowired
    private ItemSkuDao itemSkuDao;

    @Autowired
    private InStockOrderService inStockOrderService;


    @Override
    public int editItemSku(ItemSku itemSku, Double price) {
        if (price == null) {
            throw new ParamException("price不能为空");
        }
        itemSku.setPrice(OrderUtils.encodePrice(price));
        if (itemSku.getId() == null) {
            // --新增--
            itemSku.setCreateTime(new Date());
            itemSku.setTotalStock(itemSku.getStock());
            //查重
            ItemSkuQuery query = new ItemSkuQuery();
            query.setItemId(itemSku.getItemId());
            query.setColor(itemSku.getColor());
            query.setItemSize(itemSku.getItemSize());
            query.setUserId(itemSku.getUserId());
            if (itemSkuDao.countByQuery(query) > 0) {
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为"
                        ,query.getColor(),","
                        ,query.getItemSize(), ","
                        ,"规格的sku"));
            }
            ValidateUtils.validate(itemSku);
            int result = itemSkuDao.insert(itemSku);

            if (itemSku.getStock() > 0) {
                //生成一条入库单
                inStockOrderService.inStock(itemSku, itemSku.getStock());
            }
            return result;
        } else {
            // --更新--
            ItemSkuQuery query = new ItemSkuQuery();
            query.setItemId(itemSku.getItemId());
            query.setColor(itemSku.getColor());
            query.setItemSize(itemSku.getItemSize());
            ItemSku dbItemSku = itemSkuDao.selectOneByQuery(query);
            if (dbItemSku != null && dbItemSku.getId() != itemSku.getId()) {
                //其他尺码有同名
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为"
                        ,query.getColor(),","
                        ,query.getItemSize(), ","
                        ,"规格的sku"));
            }
            ItemSku updateItemSku = new ItemSku();
            //只允许更新一下数据
            updateItemSku.setItemSize(itemSku.getItemSize());
            updateItemSku.setColor(itemSku.getColor());
            return itemSkuDao.updateByPrimaryKeySelective(updateItemSku);
        }
    }


    @Override
    public PageBean itemSkuPageList(ItemSkuQuery itemSkuQuery) {
        itemSkuQuery.setSortBy(new SortBy("color", SortBy.DirectionEnum.ASC));
        int itemCount = itemSkuDao.countByQuery(itemSkuQuery);
        PageBean<ItemSku> itemSkuPageBean = new PageBean<>(itemCount,itemSkuQuery);
        List<ItemSku> itemSkuList = itemSkuDao.selectByItemSkuQuery(itemSkuQuery);
        itemSkuPageBean.setList(itemSkuList);
        return itemSkuPageBean;
    }

    @Override
    public ItemSku findById(Long itemSkuId) {
        if (itemSkuId == null) {
            throw new ParamException("itemSkuId不能为空");
        }
        return itemSkuDao.selectByPrimaryKey(itemSkuId);
    }
}
