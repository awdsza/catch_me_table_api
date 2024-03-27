package com.app.catchmetable.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class CustomerRequestDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"
    ,message = "이메일 양식에 맞게 입력해주세요.")
    private String email;

    @NotBlank(message="비밀번호는 필수입니다.")

    private String userPw;

    private String nickname;
    private String introduce;

    public void changeEncodePw(String encodePw){
        this.userPw = encodePw;
    }

    public static CustomerRequestDto createRequestDto(String email,String userPw, String nickname,String introduce){
            return new CustomerRequestDto(email,userPw,nickname,introduce);
    }

}
