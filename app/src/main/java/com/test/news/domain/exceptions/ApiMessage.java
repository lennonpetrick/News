package com.test.news.domain.exceptions;

public class ApiMessage {

    private String status,
                   code,
                   message;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
