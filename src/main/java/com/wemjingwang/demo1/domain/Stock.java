package com.wemjingwang.demo1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;
    @Column(nullable = false)
    private Long stockSellerId;//必须是商家管理员
    @Column(nullable = false)
    private String stockGoodName;//商品名称
    @Column(nullable = false,scale = 1,precision =10)//商品单价
    private BigDecimal stockGoodPrice;
    @Column(nullable = false)//商品数量
    private BigDecimal stockGoodCount;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stockInDate;
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")//创建日期
    private Timestamp stockCreatTime;
    @Column(columnDefinition = "timestamp  DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP")//修改日期
    private Timestamp stockUpdateTime;
}
