package com.xander.myblog.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Xander on 2018-11-05.
 */
@Controller
public class HomeController {
    @GetMapping("error")
    public String error() {
        return "error";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("date")
    @ResponseBody
    public Date date() {
        return new Date();
    }
}