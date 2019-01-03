package com.wemjingwang.demo1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class VenderStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venderStockId;
    @Column
    private String venderGoodName;//售货机商品名称
    @Column(precision = 10)
    private BigDecimal venderGoodCount;//售货机产品个数
    @Column(precision = 10,scale = 1)
    private BigDecimal venderGoodPrice;//售货机产品单价
    @Column
    private Long VenderId;//售货机id
}
