package com.app.catchmetable.dto;

import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private String message;
    private T  result;

    public ResponseDto(String message, T result) {
        this.message = message;
        this.result = result;
    }
}
