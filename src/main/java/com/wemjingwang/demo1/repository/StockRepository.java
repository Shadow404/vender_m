package com.wemjingwang.demo1.repository;

import com.wemjingwang.demo1.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
   /*查询库存*/
    @Query(nativeQuery = true,value = "select * from stock where stock_seller_id =?1")
    List<Stock> findListBySellerId(Long sellerId);
    @Query(nativeQuery = true,value = "select * from stock where stock_seller_id =?1 and stock_good_count>0")
    List<Stock> findListBySellerIdAndStockGoodCount(Long sellerId);
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "INSERT INTO stock(stock.stock_good_name,stock.stock_good_count,stock.stock_good_price,stock.stock_in_date,stock.stock_seller_id) VALUES(?1,?2,?3,?4,?5)")
    int addGoods(String goodName, BigDecimal goodCount, BigDecimal goodPrice, Date stockInDate, Long sellerId);
    /*查询商品库存*/
    @Query(nativeQuery = true,value = "select stock_good_count from stock where stock_id=?1")
    BigDecimal findCountById(long stockId);
    /*修改商品数目*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE stock SET stock_good_count=stock_good_count-?1 WHERE stock_id=?2")
    int delStockCount(BigDecimal venderCount,long stockId);
    /*增加库存数量*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update stock set stock_good_count =stock_good_count+?1 where  stock_id=?2")
    int addGoodsCount(BigDecimal stockGoodCount, long stockId);
    /*查询库存*/
    @Query(nativeQuery = true,value = "select * from stock where stock_id=?1")
    Stock findByStockId(long stockId);
    /*修改库存商品信息*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE stock set stock_good_name=?1,stock_good_count=?3,stock_good_price=?2,stock_in_date=?4 WHERE stock_id =?5")
    int editStock(String goodName, BigDecimal goodPrice, BigDecimal goodCount, Date goodInDateDate, long stockId);
}
