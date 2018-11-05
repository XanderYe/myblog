package com.xander.myblog.constant;

import lombok.Getter;

/**
 * Created by Xander on 2018-11-05.
 */
@Getter
public enum ErrorCode {
    AUTHENTICATION_EXCEPTION(10000, "认证异常！"),
    ACCOUNT_OR_PASSWORD_ERROR(10001, "账号或密码错误！"),
    MAIL_VERIFICATION_EXCEPTION(10002, "校验码时效已过或已被使用或校验码不正确！"),
    PARAMETER_ERROR(10003, "参数错误"),
    PARAMETER_EMPTY(10004, "参数不为空"),
    CAPTCHA_ERROR(10005, "请输入正确的验证码！");

    private int code;
    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}