package com.app.catchmetable.dto;

import lombok.Getter;

@Getter
public class FailResponseDto {
    private String message;

    public FailResponseDto(String message) {
        this.message = message;
    }
}
