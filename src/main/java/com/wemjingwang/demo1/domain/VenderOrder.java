package com.wemjingwang.demo1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class VenderOrder {
    @Id
    @GenericGenerator(name="sss",strategy ="uuid")
    @GeneratedValue(generator = "sss",strategy = GenerationType.TABLE)
    private String orderId;//订单id
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp orderCreateTime;//订单日期
    @Column
    private long venderId;//售货机id
    @Column
    private long sellerId;//商家id
    @Column
    private String goodName;//商品名称
    @Column(scale = 1,precision = 10)
    private BigDecimal goodPrice;//商品价格
    @Column(precision = 10)
    private BigDecimal goodCount;//商品个数

}
