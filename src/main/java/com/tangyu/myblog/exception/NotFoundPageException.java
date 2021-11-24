package com.tangyu.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hxy
 * @create 2021-11-08 12:37
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundPageException extends RuntimeException {

    public NotFoundPageException() {
    }

    public NotFoundPageException(String message) {
        super(message);
    }

    public NotFoundPageException(String message, Throwable cause) {
        super(message, cause);
    }

}
