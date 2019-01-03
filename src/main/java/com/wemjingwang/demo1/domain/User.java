package com.wemjingwang.demo1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;//id
    @Column(nullable = false,unique =true)
    private String userName;//用户名
    @Column(nullable = false)
    private String userPassWord;//密码
    @Column
    private String userRealName;//真实姓名
    @Column
    private String userPhone;//手机号
    @Column(nullable = false)//0管理员
    private int userType;
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp userCreatTime;
    @Column(columnDefinition = "timestamp  DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp userUpdateTime;
}
