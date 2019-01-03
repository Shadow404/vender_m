package com.wemjingwang.demo1.controller;

import com.wemjingwang.demo1.domain.Seller;
import com.wemjingwang.demo1.domain.Stock;
import com.wemjingwang.demo1.domain.Url;
import com.wemjingwang.demo1.domain.Vender;
import com.wemjingwang.demo1.repository.SellerRepository;
import com.wemjingwang.demo1.repository.UrlRepository;
import com.wemjingwang.demo1.repository.VenderRepository;
import com.wemjingwang.demo1.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@Controller
public class PageController {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private VenderRepository venderRepository;
    @RequestMapping("/")
    public String login(HttpServletRequest request){

        return "login.html";
    }
    @RequestMapping("/index")
    public String index(RedirectAttributes redirectAttributes,HttpServletRequest request){
        Object seller= request.getSession().getAttribute("seller");

        if (seller==null){
            String msg="请先登录！";
            redirectAttributes.addFlashAttribute("msg",msg);
            return "redirect:/";
        }

        return "index.html";
    }
    @RequestMapping("/doLogin")
    public String doLogin(RedirectAttributes redirectAttributes, ModelMap modelMap, HttpServletRequest request, @RequestParam String sellerName, @RequestParam String sellerPassWord){
        log.info("用户名 {} 密码 {}",sellerName,sellerPassWord);
        Seller seller=sellerRepository.findBySellerName(sellerName);
        String msg="登录失败！请输入正确的用户名或者密码！";
        if(seller!=null&&sellerPassWord.equals(seller.getSellerPassWord())){
            List<Url> urlList=urlRepository.findByUrlType(seller.getSellerType());
            request.getSession().setAttribute("seller",seller);//设置session
            msg="登录成功！";
            int otherSellerType=0;//判断同一家店的操作员或者管理员
            if(seller.getSellerType()==1){
                otherSellerType=2;
            }
            else {
                otherSellerType=1;
            }
            long otherSellerId=sellerRepository.findBySellerShopName(seller.getSellerShopName(),otherSellerType);
            if(otherSellerId!=0){
                request.getSession().setAttribute("otherSellerId",otherSellerId);
            }
            modelMap.addAttribute("sellerName",seller.getSellerRealName());
            modelMap.addAttribute("urlList",urlList);
            modelMap.addAttribute("msg",msg);
            return "forward:index";

        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/";
    }
    @RequestMapping("/stock")
    public String stock(HttpServletRequest request,ModelMap modelMap){
        Seller seller= (Seller) request.getSession().getAttribute("seller");
        Long sellerId=seller.getSellerId();
        List<Stock> stocks=stockService.findListBySellerId(sellerId);
        modelMap.addAttribute("stocks",stocks);
        modelMap.addAttribute("size",stocks.size());
        log.info("{} stocks",stocks);
        return "stock";
    }
    @RequestMapping("buy")
    public String buy(ModelMap modelMap){
        List<Vender> venders=venderRepository.findAll();
        modelMap.addAttribute("venders",venders);
        return "buy.html";
    }
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome.html";
    }
}
