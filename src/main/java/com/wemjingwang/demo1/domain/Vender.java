package com.wemjingwang.demo1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Vender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venderId;
    @Column(nullable = false)
    private String venderName;
    @Column
    private long venderSellerId;//必须是操作员
    @Column
    private String venderLongitude ;//经度
    @Column
    private String venderLatitude;//纬度

}
