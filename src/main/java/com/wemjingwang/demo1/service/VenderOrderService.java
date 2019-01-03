package com.wemjingwang.demo1.service;

import com.wemjingwang.demo1.domain.VenderOrder;

import java.util.List;
import java.util.Map;

public interface VenderOrderService {
    List<Map<String,Object>> findOrdersBySeller(long sellerId);

    List<Map<String, Object>> findOrderGoodBySeller(long sellerId);
}
