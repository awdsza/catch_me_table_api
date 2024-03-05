package com.app.catchmetable.dto;

import com.app.catchmetable.domain.CloseState;
import com.app.catchmetable.domain.EnterShopType;
import com.app.catchmetable.domain.FoodCategory;
import com.app.catchmetable.domain.RestaurantFoodCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RestaurantRequestDto {

    @NotBlank(message = "사업자번호는 필수입니다.")
    private String restaurantNumber;

    @NotBlank(message="비밀번호는 필수입니다.")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$")
//    @Length(min = 8, max = 10)
    private String userPW;

    @NotBlank(message = "가게 이름은 필수입니다.")
    private String restaurantName;
    @NotBlank(message = "가게 주소는 필수입니다.")
    private String restaurantAddress;
    private String restaurantTelephoneNumber;
    private String restaurantIntroduce;

    private CloseState closeState;
    private List<String> restaurantFoodCategoryList = new ArrayList<>();


    private Integer foodMinPrice;
    private Integer foodMaxPrice;
    private Integer restaurantLimitPeople;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    @NotBlank(message="오픈시간 입력은 필수입니다.")
    private String openTime;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    @NotBlank(message="마감시간 입력은 필수입니다.")
    private String closeTime;

    private Boolean hasBreakTime;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    private String breakStartTime;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    private String breakEndTime;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    private String waitReservationStartTime;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    private String waitReservationEndTime;

    private Integer waitReservationMinLimitPeople;
    private Integer waitReservationMaxLimitPeople;

    private EnterShopType enterShopType;

    private Integer reservationHourInterval;
    private Integer reservationMinuteInterval;

    private Integer waitAmLimitTeam;
    private Integer waitPmLimitTeam;


}
