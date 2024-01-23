package com.app.catchmetable.dto;

import com.app.catchmetable.domain.EnterShopType;
import com.app.catchmetable.domain.FoodCategory;
import com.app.catchmetable.domain.RestaurantFoodCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantRegistRequestDto {

    @NotBlank(message = "사업자번호는 필수입니다.")
    private String restaurantNumber;
    @NotBlank(message = "가게 이름은 필수입니다.")
    private String restaurantName;
    @NotBlank(message = "가게 주소는 필수입니다.")
    private String restaurantAddress;
    private String restaurantTelephoneNumber;
    private String restaurantIntroduce;


    private List<String> restaurantFoodCategoryList = new ArrayList<>();


    private int foodMinPrice;
    private int foodMaxPrice;
    private int restaurantLimitPeople;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    @NotBlank(message="오픈시간 입력은 필수입니다.")
    private String openTime;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
    @NotBlank(message="마감시간 입력은 필수입니다.")
    private String closeTime;

    private boolean hasBreakTime;

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

    private int waitReservationMinLimitPeople;
    private int waitReservationMaxLimitPeople;

    @NotBlank(message = "입점 구분값을 선택해주세요.(실시간웨이팅, 예약)")
    private String enterShopType;

    private int reservationHourInterval;
    private int reservationMinuteInterval;

    private int waitAmLimitTeam;
    private int waitPmLimitTeam;


}
