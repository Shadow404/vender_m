package com.wemjingwang.demo1.service.serviceImpl;

import com.wemjingwang.demo1.domain.Vender;
import com.wemjingwang.demo1.domain.VenderOrder;
import com.wemjingwang.demo1.repository.VenderOrderRepository;
import com.wemjingwang.demo1.repository.VenderStockRepository;
import com.wemjingwang.demo1.service.VenderOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VenderOrderServiceImpl implements VenderOrderService {
    @Autowired
    private VenderOrderRepository venderOrderRepository;
    @Override
    public List<Map<String,Object>> findOrdersBySeller(long sellerId) {
        List<Map<String,Object>> list=venderOrderRepository.findOrdersBySeller(sellerId);
        return list;
    }

    @Override
    public List<Map<String, Object>> findOrderGoodBySeller(long sellerId) {
        List<Map<String,Object>> list=venderOrderRepository.findOrderGoodBySeller(sellerId);
        return list;
    }
}
