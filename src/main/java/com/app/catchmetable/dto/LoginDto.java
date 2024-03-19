package com.app.catchmetable.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {
    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPw;
}
