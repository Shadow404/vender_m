package com.wemjingwang.demo1.service.serviceImpl;

import com.wemjingwang.demo1.domain.Seller;
import com.wemjingwang.demo1.repository.SellerRepository;
import com.wemjingwang.demo1.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<Seller> findAllSeller() {
        return sellerRepository.findAll();
    }
    /*添加商家*/
    @Override
    public String addSeller(Seller seller) {
        String sellerName=seller.getSellerName();
        String sellerPassword=seller.getSellerPassWord();
        String sellerRealName=seller.getSellerRealName();
        String sellerPhn=seller.getSellerPhone();
        String sellerShopName=seller.getSellerShopName();
        int sellerType=seller.getSellerType();
        int result=sellerRepository.addSeller(sellerName,sellerPassword,sellerRealName,sellerPhn,sellerShopName,sellerType);
        String message="添加商家失败！";
        if(result>0){
            message="添加商家成功！";
        }
        return message;
    }
    /*查询商家信息by ID*/
    @Override
    public Seller findSellerById(long sellerId) {
        return sellerRepository.getOne(sellerId);
    }

    @Override
    public String editSeller(Seller seller) {
        String message="修改失败！";
        String sellerName=seller.getSellerName();
        String sellerPassword=seller.getSellerPassWord();
        String sellerRealName=seller.getSellerRealName();
        String sellerPhn=seller.getSellerPhone();
        String sellerShopName=seller.getSellerShopName();
        long sellerId=seller.getSellerId();
        Seller old_seller=sellerRepository.getOne(sellerId);
        sellerRepository.changeSellerShop(sellerShopName,old_seller.getSellerShopName());
        int result=sellerRepository.editSeller(sellerName,sellerPassword,sellerPhn,sellerRealName,sellerShopName,sellerId);
        if(result>0){
            message="修改成功！";
        }
        return message;
    }

    @Override
    public Map<String, Object> delSeller(long sellerId) {
        Map<String,Object> map=new HashMap<>();
        int result=sellerRepository.delSeller(sellerId);
        String message="删除失败！";
        if(result>0){
            message="删除成功！";
        }
        map.put("message",message);
        return map;
    }
}
