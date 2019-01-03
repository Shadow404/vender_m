package com.wemjingwang.demo1.controller.api;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wemjingwang.demo1.domain.Seller;
import com.wemjingwang.demo1.domain.Stock;
import com.wemjingwang.demo1.domain.Vender;
import com.wemjingwang.demo1.service.StockService;
import com.wemjingwang.demo1.service.VenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class VenderController {
    @Autowired
    private VenderService venderService;
    @Autowired
    private StockService stockService;
    @RequestMapping("/vender")
    public String vender(ModelMap modelMap, HttpServletRequest request){
        Seller seller= (Seller) request.getSession().getAttribute("seller");
        Long sellerId=seller.getSellerId();
        List<Vender> venders=venderService.findVenderBySellerId(sellerId);
        modelMap.addAttribute("size",venders.size());
        modelMap.addAttribute("venders",venders);
        return "vender.html";
    }
    @RequestMapping("/position")
    public String position(@RequestParam long venderId,ModelMap modelMap){
        Vender vender=venderService.findVenderById(venderId);
        modelMap.addAttribute("vender",vender);
        return "position.html";
    }
    @RequestMapping("addVender")
    public String addVender(ModelMap modelMap,HttpServletRequest request){
        Seller seller=(Seller) request.getSession().getAttribute("seller");
        modelMap.addAttribute("sellerId",seller.getSellerId());
        return "addVender.html";
    }
    @RequestMapping("api/doAddVender")
    @ResponseBody
    public String doAddVender(Vender vender){
        log.info("{}add vender",vender);
        String message=venderService.addVender(vender);
        return message;
    }
    @RequestMapping("editVender")
    public String editVender(@RequestParam long venderId,ModelMap modelMap){
        Vender vender=venderService.findVenderById(venderId);
        modelMap.addAttribute("vender",vender);
        return "editVender.html";
    }
    @ResponseBody
    @RequestMapping("/api/doEditVender")
    public String doEditVender(Vender vender){
        String message=venderService.editVender(vender);
        return message;
    }
    @ResponseBody
    @RequestMapping("api/delVender")
    public Map<String,String> delVender(long venderId){
        String message=venderService.delVender(venderId);
        Map map=new HashMap();
        map.put("message",message);
        return map;
    }


}
