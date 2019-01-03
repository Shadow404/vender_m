package com.wemjingwang.demo1.repository;

import com.wemjingwang.demo1.domain.User;
import com.wemjingwang.demo1.domain.Vender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VenderRepository extends JpaRepository<Vender,Long> {
    /*查询商家自己的售货机*/
    @Query(nativeQuery = true,value = "select * from vender where vender_seller_id=?1")
    List<Vender> findVenderBySellerId(Long sellerId);
    /*添加售货机*/
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "insert into vender(vender_name,vender_latitude,vender_longitude,vender_seller_id) values (?1,?2,?3,?4)")
    int addVender(String venderName, String venderLat, String venderLot, long sellerId);
    /*修改售货机*/
    @Modifying
    @Transactional
    @Query(value = "UPDATE vender SET vender_name=?1,vender_longitude=?2,vender_latitude=?3 where vender_id=?4",nativeQuery = true)
    int editVender(String venderName, String venderLong, String venderLant, long venderId);

    /*删除售货机*/
    @Modifying
    @Transactional
    @Query(value = "delete from vender where vender_id=?1",nativeQuery = true)
    int delVenderByVenderId(long venderId);
}
