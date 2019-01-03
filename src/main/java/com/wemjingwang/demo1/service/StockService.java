package com.wemjingwang.demo1.service;

import com.wemjingwang.demo1.domain.Stock;

import java.util.List;
import java.util.Map;

public interface StockService {
    List<Stock> findListBySellerId(Long sellerId);

    String addGoods(Stock stock);

    List<Stock> findListBySellerIdAndStockGoodCount(long otherSellerId);

    Map<String, Object> searchGoodCount(long stockId);

    String addGoodsCount(Stock stock);

    Stock findStockById(long stockId);

    String editStock(Stock stock,long otherId);
}
