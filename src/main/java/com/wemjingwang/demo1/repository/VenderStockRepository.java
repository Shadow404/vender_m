package com.wemjingwang.demo1.repository;

import com.wemjingwang.demo1.domain.VenderOrder;
import com.wemjingwang.demo1.domain.VenderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface VenderStockRepository extends JpaRepository<VenderStock,Long> {
    /*查询某个售货机库存*/
    @Modifying
    @Query(value = "SELECT  SUM(vender_stock.vender_good_count) vender_good_count,vender_stock.vender_good_name,vender_stock.vender_good_price FROM vender_stock WHERE vender_stock.vender_id=?1 GROUP BY vender_good_name",nativeQuery = true)
    List<Map<String,Object>> findAllByVenderId(long venderId);
    /*删除某个售货机库存里的某个商品*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from vender_stock where vender_good_name=?1 and vender_id=?2")
    int delVenderStockByGoodNameAndVenderId(String goodName,long venderId);
    /*删除某个售货机所有商品*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from vender_stock where vender_id=?1")
    int delVenderStockById(long venderId);
    /*更新售货机商品内容*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE  vender_stock SET vender_good_name=?1,vender_good_price=?2 WHERE vender_id in(SELECT vender_id from vender WHERE vender_seller_id=?4) and vender_good_name=?3")
    int changeVenderStockInfo(String goodName, BigDecimal goodPrice, String stockGoodName, long otherId);
    /*查询库存*/
    @Query(nativeQuery = true,value = "SELECT  * FROM vender_stock WHERE vender_stock.vender_id=?1 AND vender_good_name=?2")
    List<VenderStock> findAllByVenderId(long venderId, String goodName);
    /*购买*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update vender_Stock set vender_good_count=vender_good_count-?1 where vender_stock_id=?2")
    int delVenderStockByBuyer(BigDecimal goodCount, Long venderStockId);

}
