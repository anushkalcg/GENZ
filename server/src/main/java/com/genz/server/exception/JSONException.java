package com.genz.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JSONException extends RuntimeException{
    public JSONException(String message) {
        super(message);
    }
}
