package com.xander.myblog.dto;

import lombok.Data;

/**
 * Created by Xander on 2018-11-05.
 */
@Data
public class UserDto {
    private long uid;
    private String username;
    private String token;
}