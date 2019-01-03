package com.wemjingwang.demo1.service;

import com.wemjingwang.demo1.domain.Vender;

import java.util.List;

public interface VenderService {
    List<Vender> findVenderBySellerId(Long sellerId);

    Vender findVenderById(long venderId);

    String addVender(Vender vender);

    String editVender(Vender vender);

    String delVender(long venderId);
}
