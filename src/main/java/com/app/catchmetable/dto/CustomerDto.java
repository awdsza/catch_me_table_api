package com.app.catchmetable.dto;

import com.app.catchmetable.domain.Customer;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class CustomerDto implements Serializable {
    private String userId;
    private String nickname;
    private String introduce;

    public CustomerDto(String userId, String nickname, String introduce) {
        this.userId = userId;
        this.nickname = nickname;
        this.introduce = introduce;
    }

    public static CustomerDto createSuccessDto(Customer customer){
        return new CustomerDto(customer.getUserId(),customer.getNickname(),customer.getIntroduce());
    }
}
