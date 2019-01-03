package com.wemjingwang.demo1.service;

import com.wemjingwang.demo1.domain.Vender;
import com.wemjingwang.demo1.domain.VenderStock;

import java.util.List;
import java.util.Map;

public interface VenderStockService {

    List<Map<String,Object>> findVenderStockByVenderId(long venderId);

    String doAddVenderStock(VenderStock venderStock, long stockId);

    String delVenderStockByGoodNameAndVenderId(String goodName, long venderId);

    String delVenderStockByVenderId(long venderId);
}
