package com.wemjingwang.demo1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer urlId;
    @Column
    private String urlName;//连接名称
    @Column
    private String urlLink;//连接
    @Column
    private Integer urlType;//连接权限
}
