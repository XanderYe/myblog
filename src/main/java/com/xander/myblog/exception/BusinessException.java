package com.xander.myblog.exception;

import com.xander.myblog.constant.ErrorCode;
import org.slf4j.helpers.MessageFormatter;

/**
 * Created by Xander on 2018-11-05.
 */
public class BusinessException extends RuntimeException {
    private int code;
    private String snapshot;

    public BusinessException(ErrorCode errorCode, String snapshotFormat, Object... argArray) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public BusinessException(int code, String message, String snapshotFormat, Object... argArray) {
        super(message);
        this.code = code;
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getSnapshot() {
        return snapshot;
    }
}