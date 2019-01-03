package com.wemjingwang.demo1.controller.api;

import com.wemjingwang.demo1.domain.Seller;
import com.wemjingwang.demo1.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @RequestMapping("sellerM")
    public String sellerM(ModelMap modelMap){
        List<Seller> sellers=sellerService.findAllSeller();
        modelMap.addAttribute("sellers",sellers);
        modelMap.addAttribute("size",sellers.size());
        return "sellerM.html";
    }
    @RequestMapping("addSeller")
    public String addSeller(){
        return "addSeller.html";
    }
    @RequestMapping("api/doAddSeller")
    @ResponseBody
    public String doAddSeller(Seller seller){
        String message=sellerService.addSeller(seller);
        return message;
    }
    @RequestMapping("editSeller")
    public String editSeller(@RequestParam long sellerId,ModelMap modelMap){
        Seller seller=sellerService.findSellerById(sellerId);
        modelMap.addAttribute("seller",seller);
        return "editSeller.html";
    }
    @ResponseBody
    @RequestMapping("api/doEditSeller")
    public String doEditSeller(Seller seller){
        String message=sellerService.editSeller(seller);
        return message;
    }
    @RequestMapping("api/delSeller")
    @ResponseBody
    public Map<String,Object> delSeller(long sellerId){
        Map<String,Object> map=sellerService.delSeller(sellerId);
        return map;
    }
}
