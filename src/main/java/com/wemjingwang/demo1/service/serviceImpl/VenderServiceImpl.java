package com.wemjingwang.demo1.service.serviceImpl;

import com.wemjingwang.demo1.domain.Vender;
import com.wemjingwang.demo1.repository.VenderRepository;
import com.wemjingwang.demo1.service.VenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenderServiceImpl implements VenderService {
    @Autowired
    private VenderRepository venderRepository;
    /*查询商家所拥有的售货机*/
    @Override
    public List<Vender> findVenderBySellerId(Long sellerId) {

        return venderRepository.findVenderBySellerId(sellerId);
    }
    /*查询售货机 by id*/
    @Override
    public Vender findVenderById(long venderId) {
        return venderRepository.getOne(venderId);
    }
    /*添加售货机*/
    @Override
    public String addVender(Vender vender) {
        String venderName=vender.getVenderName();
        String venderLat=vender.getVenderLatitude();
        String venderLot=vender.getVenderLongitude();
        long sellerId=vender.getVenderSellerId();
        int result=venderRepository.addVender(venderName,venderLat,venderLot,sellerId);
        String message="添加售货机失败！";
        if(result>0){
            message="添加售货机成功！";
        }
        return message;
    }
    /*修改售货机*/
    @Override
    public String editVender(Vender vender) {
        long venderId=vender.getVenderId();
        String venderLong=vender.getVenderLongitude();
        String venderLant=vender.getVenderLatitude();
        String venderName=vender.getVenderName();
        int result=venderRepository.editVender(venderName,venderLong,venderLant,venderId);
        String message="修改失败！";
        if(result>0){
            message="修改成功！";
        }
        return message;
    }

    @Override
    public String delVender(long venderId) {
         int result=venderRepository.delVenderByVenderId(venderId);
         String message="删除失败！";
         if(result>0){
             message="删除成功！";
         }
         return message;
    }
}
