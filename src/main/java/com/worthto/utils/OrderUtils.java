package com.worthto.utils;

/**
 * Created by gezz on 2017/4/22.
 */
public class OrderUtils {
    public static final Integer PRICE_UNIT = 100;

    public final static Integer encodePrice(Double price) {
        Double retprice = new Double(new Double(PRICE_UNIT * new Double(price)));
        return retprice.intValue();
    }

    public final static Double decodePrice(Integer price) {
        Double retPrice = price.doubleValue()/PRICE_UNIT;
        return retPrice;
    }
}
