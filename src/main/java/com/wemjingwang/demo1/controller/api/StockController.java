package com.wemjingwang.demo1.controller.api;

import com.wemjingwang.demo1.domain.Seller;
import com.wemjingwang.demo1.domain.Stock;
import com.wemjingwang.demo1.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StockController {
    @Autowired
    private StockService stockService;
    @RequestMapping("/addGoods")
    public String addGoods(HttpServletRequest request, ModelMap modelMap){
    Seller seller= (Seller) request.getSession().getAttribute("seller");
    Long sellerId=seller.getSellerId();
    modelMap.addAttribute("sellerId",sellerId);
    return "addGoods.html";
    }
    @RequestMapping("api/doAddGoods")
    @ResponseBody
    public String doAddGoods(Stock stock){
        String message=stockService.addGoods(stock);
        return message;
    }
    @RequestMapping("addStockCount")
    public String addStockCount(@RequestParam long stockId,ModelMap modelMap){
        modelMap.addAttribute("stockId",stockId);
        return "addStockCount.html";
    }
    @RequestMapping("/api/doAddStockCount")
    @ResponseBody
    public String stockGoodCount(Stock stock){
        String message=stockService.addGoodsCount(stock);
        return message;
    }
    @RequestMapping("editStock")
    public String editStock(@RequestParam long stockId,ModelMap modelMap){
        Stock stock=stockService.findStockById(stockId);
        modelMap.addAttribute("stock",stock);
        return "editStock.html";
    }
    @ResponseBody
    @RequestMapping("/api/doeditGoods")
    public String doeditStock(Stock stock,HttpServletRequest request){
        long otherId=(long)request.getSession().getAttribute("otherSellerId");
        String message=stockService.editStock(stock,otherId);
        return message;
    }
}
