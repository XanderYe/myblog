package com.xander.myblog.web;

import com.xander.myblog.bean.ResultBean;
import com.xander.myblog.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Xander on 2018-11-05.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn(e.getMessage(), e);
        return super.handleExceptionInternal(e, body, headers, status, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBusinessException(BusinessException e) {
        log.warn(e.getSnapshot(), e);
        return new ResponseEntity<>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}