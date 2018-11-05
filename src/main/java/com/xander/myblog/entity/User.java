package com.xander.myblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Xander on 2018-11-05.
 */
@Data
@TableName("user")
public class User {

    @TableId(value = "uid", type = IdType.AUTO)
    private long uid;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("token")
    private String token;
}