package com.wemjingwang.demo1.service;

import com.wemjingwang.demo1.domain.Seller;

import java.util.List;
import java.util.Map;

public interface SellerService {
    List<Seller> findAllSeller();

    String addSeller(Seller seller);

    Seller findSellerById(long sellerId);

    String editSeller(Seller seller);

    Map<String, Object> delSeller(long sellerId);
}
