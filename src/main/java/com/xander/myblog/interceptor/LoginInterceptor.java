package com.xander.myblog.interceptor;

import com.xander.myblog.constant.ErrorCode;
import com.xander.myblog.entity.User;
import com.xander.myblog.exception.BusinessException;
import com.xander.myblog.service.IUserService;
import com.xander.myblog.web.RequestContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xander on 2018-11-05.
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IUserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String originHeader = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Access-Control-Allow-Origin,X-User-Token");

        //测试
        /*if(true){
            return true;
        }*/
        //不拦截OPTION请求
        if (method.equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        //不拦截/user/login登录请求
        if (uri.equals(request.getContextPath() + "/user/login")) {
            return true;
        }
        String userToken = request.getHeader("X-User-Token");
        log.info("remoteAddr={},  method={}, uri={}, userToken={}", remoteAddr, method, uri, userToken);

        if (userToken == null) {
            throw new BusinessException(ErrorCode.AUTHENTICATION_EXCEPTION, "remoteAddr={}, method={}, uri={}, userToken={}", remoteAddr, method, uri, userToken);
        }

        User user = userService.selectByToken(userToken);
        if (user == null) {
            throw new BusinessException(ErrorCode.AUTHENTICATION_EXCEPTION, "remoteAddr={}, method={}, uri={}, userToken={}, user={}", remoteAddr, method, uri, userToken, user);
        }

        RequestContextHolder.set(user);
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContextHolder.reset();
    }
}