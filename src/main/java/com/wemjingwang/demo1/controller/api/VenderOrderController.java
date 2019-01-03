package com.wemjingwang.demo1.controller.api;

import com.wemjingwang.demo1.domain.Seller;
import com.wemjingwang.demo1.domain.VenderOrder;
import com.wemjingwang.demo1.repository.VenderOrderRepository;
import com.wemjingwang.demo1.service.VenderOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
public class VenderOrderController {
    @Autowired
    private VenderOrderService venderOrderService;
    @RequestMapping("/venderStatic")
    public String venderStatic(ModelMap modelMap, HttpServletRequest request){

        return "venderStatic.html";
    }
    @RequestMapping("api/venderOrderChart")
    @ResponseBody
    public Map<String,Object> venderOrderChart(HttpServletRequest request){
        long sellerId= (long) request.getSession().getAttribute("otherSellerId");

        Map map=new HashMap();
        List<Map<String,Object>> orders=venderOrderService.findOrdersBySeller(sellerId);
        map.put("list",orders);
        return map;
    }
    @RequestMapping("api/venderOrderGoodChart")
    @ResponseBody
    public Map<String,Object> venderOrderGoodChart(HttpServletRequest request){
        long sellerId= (long) request.getSession().getAttribute("otherSellerId");
        log.info("seller id {}",sellerId);
        Map map=new HashMap();
        List<Map<String,Object>> ordergoods=venderOrderService.findOrderGoodBySeller(sellerId);
        map.put("list1",ordergoods);
        log.info("order map {}",ordergoods.size());
        return map;
    }
}
