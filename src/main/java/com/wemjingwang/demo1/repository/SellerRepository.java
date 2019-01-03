package com.wemjingwang.demo1.repository;

import com.wemjingwang.demo1.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    /*根据商家名称查询*/
    @Query(nativeQuery = true,value = "select * from seller where seller_name =?1")
    Seller findBySellerName(String sellerName);
    /*查询同一家店的另一个操作员或者管理员*/
    @Query(value = "select seller_id from seller where seller_shop_name=?1 and seller_type=?2",nativeQuery = true)
    long findBySellerShopName(String sellerShopName, int otherSellerType);
    /*添加商家*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "insert into seller (seller_name,seller_pass_word,seller_real_name,seller_phone,seller_shop_name,seller_type)values(?1,?2,?3,?4,?5,?6)")
    int addSeller(String sellerName, String sellerPassword, String sellerRealName, String sellerPhn, String sellerShopName, int sellerType);
    /*编辑商家*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update seller set seller_name=?1,seller_pass_word=?2,seller_real_name=?4,seller_phone=?3,seller_shop_name=?5 where seller_id=?6")
    int editSeller(String sellerName, String sellerPassword, String sellerPhn, String sellerRealName, String sellerShopName, long sellerId);
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update seller set seller_shop_name=?1 where seller_shop_name=?2")
    void changeSellerShop(String sellerShopName, String old_sellerShopName);
    /*删除商家*/
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from seller where seller_id=?1")
    int delSeller(long sellerId);
}
