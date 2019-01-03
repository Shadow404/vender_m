package com.wemjingwang.demo1.repository;

import com.wemjingwang.demo1.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url,Integer> {
    /*根据商家类型查询Url*/
    @Query(nativeQuery = true,value = "select * from url where url_type=?1")
    List<Url> findByUrlType(int sellerType);
}
