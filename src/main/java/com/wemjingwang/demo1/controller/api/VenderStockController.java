package com.wemjingwang.demo1.controller.api;

import com.wemjingwang.demo1.domain.Seller;
import com.wemjingwang.demo1.domain.Stock;
import com.wemjingwang.demo1.domain.Vender;
import com.wemjingwang.demo1.domain.VenderStock;
import com.wemjingwang.demo1.service.StockService;
import com.wemjingwang.demo1.service.VenderService;
import com.wemjingwang.demo1.service.VenderStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
public class VenderStockController {
    @Autowired
    private VenderStockService venderStockService;
    @Autowired
    private StockService stockService;
    @Autowired
    private VenderService venderService;
    @RequestMapping("/venderStockItem")
    public String venderStockItem(@RequestParam long venderId, ModelMap modelMap){
        List<Map<String,Object>> venderStocks=venderStockService.findVenderStockByVenderId(venderId);
        modelMap.addAttribute("venderStocks",venderStocks);
        return "venderStockItem.html";
    }
    @RequestMapping("addVenderStock")
    public String addVenderStock(@RequestParam long venderId, HttpServletRequest request, ModelMap modelMap){
        long otherSellerId= (long) request.getSession().getAttribute("otherSellerId");
        List<Stock> stocks=stockService.findListBySellerIdAndStockGoodCount(otherSellerId);
        modelMap.addAttribute("stocks",stocks);
        modelMap.addAttribute("venderId",venderId);
        return "addVenderStock.html";
    }
    @ResponseBody
    @RequestMapping("/api/searchGoodCount")
    public Map<String,Object> searchGoodCount(String stockId){
        long l_stock_id=Long.valueOf(stockId);
        Map<String,Object> map=stockService.searchGoodCount(l_stock_id);
        log.info("map{}",map);
        return map;
    }
    @ResponseBody
    @RequestMapping("api/doAddVenderStock")
    public String doAddVenderStock(VenderStock venderStock,long stockId){
        String message=venderStockService.doAddVenderStock(venderStock,stockId);
        log.info("{} vender {} stock id",venderStock,stockId);
        return message;
    }
    @RequestMapping("/venderStock")
    public String venderStock(ModelMap modelMap, HttpServletRequest request){
        Seller seller= (Seller) request.getSession().getAttribute("seller");
        Long sellerId=seller.getSellerId();
        List<Vender> venders=venderService.findVenderBySellerId(sellerId);
        modelMap.addAttribute("size",venders.size());
        modelMap.addAttribute("venders",venders);
        return "venderStock.html";
    }
    @RequestMapping("venderStockItemOperate")
    public String venderOperate(@RequestParam long venderId,ModelMap modelMap){
        List<Map<String,Object>> venderStocks=venderStockService.findVenderStockByVenderId(venderId);
        modelMap.addAttribute("venderStocks",venderStocks);
        modelMap.addAttribute("venderId",venderId);
        return "venderStockItemOperate.html";
    }
    @ResponseBody
    @RequestMapping("api/delVenderStock")
    public Map<String ,Object> delVenderStock(@RequestParam String goodName,@RequestParam long venderId){
        Map<String,Object> map=new HashMap<>();
        String message=venderStockService.delVenderStockByGoodNameAndVenderId(goodName,venderId);
        map.put("message",message);
        return map;
    }
    @ResponseBody
    @RequestMapping("api/delVenderStockById")
    public Map<String ,Object> delVenderStockById(@RequestParam long venderId){
        Map<String,Object> map=new HashMap<>();
        String message=venderStockService.delVenderStockByVenderId(venderId);
        map.put("message",message);
        return map;
    }
}
