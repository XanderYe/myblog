package com.xander.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xander.myblog.constant.Constant;
import com.xander.myblog.constant.ErrorCode;
import com.xander.myblog.dto.UserDto;
import com.xander.myblog.entity.User;
import com.xander.myblog.exception.BusinessException;
import com.xander.myblog.mapper.UserMapper;
import com.xander.myblog.service.IUserService;
import com.xander.myblog.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Xander on 2018-11-05.
 */
@Slf4j
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    UserDto userDto;

    public UserDto selectById(int id) {
        userDto = new UserDto();
        if (id == 0) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR, "", "");
        }
        User user = baseMapper.selectById(id);
        userDto.setUid(user.getUid());
        userDto.setUsername(user.getUsername());
        userDto.setToken(user.getToken());
        return userDto;
    }

    public User selectByToken(String token) {
        this.userDto = new UserDto();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("token", token);
        return baseMapper.selectOne(queryWrapper);
    }

    public UserDto login(String username, String password) {
        this.userDto = new UserDto();
        if ((StringUtils.isEmpty(username)) || (StringUtils.isEmpty(password))) {
            throw new BusinessException(ErrorCode.PARAMETER_EMPTY, "username={},password={}", username, password);
        }
        User user = baseMapper.selectOne((Wrapper) new QueryWrapper().eq("username", username));
        if (user == null)
            throw new BusinessException(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR, "username={},password={}", username, password);
        try {
            if (!MD5Util.validatePwd(user.getPassword(), Constant.SALT, password)) {
                throw new BusinessException(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR, "user={}", user);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userDto.setUid(user.getUid());
        userDto.setUsername(user.getUsername());
        userDto.setToken(user.getToken());
        return userDto;
    }
}