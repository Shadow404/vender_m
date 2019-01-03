package com.wemjingwang.demo1.service.serviceImpl;

import com.wemjingwang.demo1.domain.Stock;
import com.wemjingwang.demo1.domain.VenderStock;
import com.wemjingwang.demo1.repository.StockRepository;
import com.wemjingwang.demo1.repository.VenderStockRepository;
import com.wemjingwang.demo1.service.VenderService;
import com.wemjingwang.demo1.service.VenderStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class VenderStockServiceImpl implements VenderStockService {
    @Autowired
    private VenderStockRepository venderStockRepository;
    @Autowired
    private StockRepository stockRepository;
    @Override
    /*查询某个售货机里的库存*/
    public List<Map<String,Object>> findVenderStockByVenderId(long venderId) {
        return venderStockRepository.findAllByVenderId(venderId);
    }
    /*添加售货机库存*/
    @Override
    public String doAddVenderStock(VenderStock venderStock, long stockId) {
        Stock stock=stockRepository.getOne(stockId);
        String message="";
        if(stock.getStockGoodCount().compareTo(stock.getStockGoodCount())>0){
            message="商品不足！";
            return message;
        }
        BigDecimal venderCount=venderStock.getVenderGoodCount();
        int result1=stockRepository.delStockCount(venderCount,stockId);
        if(result1>0){
            venderStock.setVenderGoodCount(venderCount);
            venderStock.setVenderGoodName(stock.getStockGoodName());
            venderStock.setVenderGoodPrice(stock.getStockGoodPrice());
            venderStockRepository.save(venderStock);
            message="添加售货机商品成功！";
        }
        return message;
    }
    /*删除某个售货机的某个商品*/
    @Override
    public String delVenderStockByGoodNameAndVenderId(String goodName, long venderId) {
        int result=venderStockRepository.delVenderStockByGoodNameAndVenderId(goodName,venderId);
        String message="清除失败！";
        if(result>0){
            message="清除成功！";
        }
        return message;
    }
    /*根据售货机id删除所有库存*/
    @Override
    public String delVenderStockByVenderId(long venderId) {
        String message="删除失败！";
        int result=venderStockRepository.delVenderStockById(venderId);
        if(result>0){
            message="删除成功！";
        }
        return message;
    }
}
