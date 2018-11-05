package com.xander.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xander.myblog.dto.UserDto;
import com.xander.myblog.entity.User;

/**
 * Created by Xander on 2018-11-05.
 */
public interface IUserService extends IService<User> {
  UserDto selectById(int paramInt);

  User selectByToken(String paramString);

  UserDto login(String paramString1, String paramString2);
}