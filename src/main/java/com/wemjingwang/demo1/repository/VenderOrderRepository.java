package com.wemjingwang.demo1.repository;


import com.wemjingwang.demo1.domain.VenderOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VenderOrderRepository extends JpaRepository<VenderOrder,Long> {
    /*根据sellerId查询*/
    @Query(nativeQuery = true,value = "SELECT count(vender_order.vender_id) as venderOrderCount,vender_name as venderName FROM (vender_order LEFT JOIN vender ON vender.vender_id=vender_order.vender_id) WHERE seller_id=?1 GROUP BY vender_order.vender_id")
    List<Map<String,Object>> findOrdersBySeller(long sellerId);
    /*根据商品类型查询*/
    @Query(value = "SELECT sum(vender_order.good_count) as goodCount,good_name as goodName FROM (vender_order LEFT JOIN vender ON vender.vender_id=vender_order.vender_id) WHERE seller_id=?1 GROUP BY vender_order.good_name",nativeQuery = true)
    List<Map<String, Object>> findOrderGoodBySeller(long sellerId);
}
