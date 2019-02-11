package com.test.news.domain.exceptions;

import com.google.gson.Gson;

public class ApiException extends RuntimeException {

    private ApiMessage mContent;

    public ApiException(String error) {
        this.mContent = new Gson().fromJson(error, ApiMessage.class);
    }

    public ApiMessage getContent() {
        return mContent;
    }

    @Override
    public String getMessage() {
        if (mContent == null) {
            return "Something went wrong :(";
        } else {
            return mContent.getMessage();
        }
    }
}
