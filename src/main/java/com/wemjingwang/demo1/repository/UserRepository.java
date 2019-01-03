package com.wemjingwang.demo1.repository;

import com.wemjingwang.demo1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /*查询是否存在user*/
    @Query(nativeQuery = true,value = "select * from user where user_name=?1")
    User findByUserName(String userName);
}
