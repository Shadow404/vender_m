package com.wemjingwang.demo1.service.serviceImpl;

import com.wemjingwang.demo1.domain.Stock;
import com.wemjingwang.demo1.domain.Vender;
import com.wemjingwang.demo1.repository.StockRepository;
import com.wemjingwang.demo1.repository.VenderStockRepository;
import com.wemjingwang.demo1.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private VenderStockRepository venderStockRepository;

    @Override
    public List<Stock> findListBySellerId(Long sellerId) {
        return stockRepository.findListBySellerId(sellerId);
    }

    @Override
    public String addGoods(Stock stock) {
        String goodName=stock.getStockGoodName();
        BigDecimal goodCount=stock.getStockGoodCount();
        BigDecimal goodPrice=stock.getStockGoodPrice();
        Date stockInDate=stock.getStockInDate();
        Long sellerId=stock.getStockSellerId();
        String message="添加商品失败！";
        int result=stockRepository.addGoods(goodName,goodCount,goodPrice,stockInDate,sellerId);
        if(result>0){
            message="添加商品成功！";
        }
        return message;
    }

    @Override
    public List<Stock> findListBySellerIdAndStockGoodCount(long otherSellerId) {
        return stockRepository.findListBySellerIdAndStockGoodCount(otherSellerId);
    }
    /*查询库存数量*/
    @Override
    public Map<String, Object> searchGoodCount(long stockId) {
        Map<String,Object> map=new HashMap<>();
        BigDecimal goodCount =stockRepository.findCountById(stockId);
        map.put("goodCount",goodCount);
        return map;
    }

    @Override
    public String addGoodsCount(Stock stock) {
        long stockId=stock.getStockId();
        BigDecimal stockGoodCount=stock.getStockGoodCount();
        int result=stockRepository.addGoodsCount(stockGoodCount,stockId);
        String message="添加库存失败！";
        if(result>0){
            message="添加库存成功！";
        }
        return message;
    }
    /*查询库存*/
    @Override
    public Stock findStockById(long stockId) {
        Stock stock=stockRepository.findByStockId(stockId);
        return stock;
    }
    /*修改库存信息*/
    @Override
    public String editStock(Stock stock, long otherId) {
        long stockId=stock.getStockId();
        BigDecimal goodPrice=stock.getStockGoodPrice();
        BigDecimal goodCount=stock.getStockGoodCount();
        String goodName=stock.getStockGoodName();
        Date goodInDateDate=stock.getStockInDate();
        Stock oldStock=stockRepository.findByStockId(stockId);
        int result1=venderStockRepository.changeVenderStockInfo(goodName,goodPrice,oldStock.getStockGoodName(),otherId);
        String message="修改失败！";
        if(result1>=0){
            int result2=stockRepository.editStock(goodName,goodPrice,goodCount,goodInDateDate,stockId);
            if(result2>0){
                message="修改成功！";
            }

        }
        return message;
    }


}
