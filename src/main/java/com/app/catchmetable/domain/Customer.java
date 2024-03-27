package com.app.catchmetable.domain;

import com.app.catchmetable.dto.CustomerRequestDto;
import com.app.catchmetable.dto.CustomerUpdateRequestDto;
import com.app.catchmetable.dto.RestaurantUpdateRequestDto;
import com.app.catchmetable.utils.StringUtils;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value="customer")
@Setter(AccessLevel.PRIVATE)
@Getter
public class Customer extends User{

    private String nickname;
    private String introduce;

    public Customer(String userId, String userPw, UserState isUse, String nickname, String introduce) {
        super(userId, userPw, isUse);
        this.nickname = nickname;
        this.introduce = introduce;
    }

    public static Customer createCustomer(CustomerRequestDto dto){
        return new Customer(dto.getEmail(),dto.getUserPw(),UserState.Y,dto.getNickname(),dto.getIntroduce());
    }
    public void deleteCustomer(){
        this.deleteUser(this);
    }
    public void updateCustomerPassword(CustomerUpdateRequestDto dto){
       this.updateUserPw(dto.getUserPw());
    }
    public void updateCustomer(CustomerUpdateRequestDto dto){
        this.setIntroduce(dto.getIntroduce());
        this.setNickname(dto.getNickname());
    }
}
