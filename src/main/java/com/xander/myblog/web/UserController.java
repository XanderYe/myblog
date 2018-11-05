package com.xander.myblog.web;

import com.xander.myblog.bean.ResultBean;
import com.xander.myblog.dto.UserDto;
import com.xander.myblog.entity.User;
import com.xander.myblog.service.IUserService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Xander on 2018-11-05.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public ResultBean login(HttpServletRequest request, String username, String password)
            throws Exception {
        return new ResultBean<>(this.userService.login(username, password));
    }

    @GetMapping("token/{token}")
    public ResultBean selectByToken(@PathVariable String token) {
        User user = RequestContextHolder.get();
        UserDto userDto = new UserDto();
        userDto.setUid(user.getUid());
        userDto.setUsername(user.getUsername());
        userDto.setToken(user.getToken());
        return new ResultBean<>(userDto);
    }

    @GetMapping("id/{id}")
    public ResultBean selectById(@PathVariable int id) {
        return new ResultBean<>(this.userService.selectById(id));
    }
}