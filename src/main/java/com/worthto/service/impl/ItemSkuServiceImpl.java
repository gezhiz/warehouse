package com.worthto.service.impl;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.validator.ValidateUtils;
import com.worthto.bean.ItemSku;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.ItemSkuDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemSkuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("itemSkuService")
public class ItemSkuServiceImpl implements ItemSkuService {

    @Autowired
    private ItemSkuDao itemSkuDao;


    @Override
    public int editItemSku(ItemSku itemSku) {
        ValidateUtils.validate(itemSku);
        if (itemSku.getId() == null) {
            // --新增--
            //查重
            ItemSkuQuery query = new ItemSkuQuery();
            query.setItemId(itemSku.getItemId());
            query.setColor(itemSku.getColor());
            query.setItemSize(itemSku.getItemSize());
            if (itemSkuDao.countByQuery(query) > 0) {
                throw new ErrcodeException(CommonUtil.combineString("已经存在名为"
                        ,query.getColor(),","
                        ,query.getItemSize(), ","
                        ,"规格的sku"));
            }
            return itemSkuDao.insert(itemSku);
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
            return itemSkuDao.updateByPrimaryKey(itemSku);
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
