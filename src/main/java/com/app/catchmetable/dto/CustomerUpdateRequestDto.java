package com.app.catchmetable.dto;

import com.app.catchmetable.domain.CloseState;
import com.app.catchmetable.domain.EnterShopType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerUpdateRequestDto {

    private String nickname;
    private String introduce;
    private String userPw;


    public static CustomerUpdateRequestDto createUpdateRequestDto(String nickname, String introduce){
        return new CustomerUpdateRequestDto(nickname,introduce,null);
    }
    public static CustomerUpdateRequestDto createUpdatePasswordRequestDto(String userPw){
        return new CustomerUpdateRequestDto(null,null,userPw);
    }
    public void changeEncodePw(String encodePw){
        this.userPw = encodePw;
    }
}
