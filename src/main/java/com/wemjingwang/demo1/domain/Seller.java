package com.wemjingwang.demo1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;//id
    @Column(nullable = false,unique =true)
    private String sellerName;//用户名
    @Column(nullable = false)
    private String sellerPassWord;//密码
    @Column
    private String sellerRealName;//真实姓名
    @Column
    private String sellerPhone;//手机号
    @Column
    private String sellerShopName;//商家名称
    @Column(nullable = false)//1管理员 2操作员
    private int sellerType;
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp sellerCreatTime;
    @Column(columnDefinition = "timestamp  DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp sellerUpdateTime;
}
