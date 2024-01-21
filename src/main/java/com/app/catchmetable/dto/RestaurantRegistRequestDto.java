package com.app.catchmetable.dto;

import com.app.catchmetable.domain.EnterShopType;
import com.app.catchmetable.domain.FoodCategory;
import com.app.catchmetable.domain.RestaurantFoodCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalTime;
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
    @Min(0)
    private int foodMinPrice;
    private int foodMaxPrice;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime openTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime closeTime;

    private boolean hasBreakTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime breakStartTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime breakEndTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime waitReservationStartTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime waitReservationEndTime;

    private int waitReservationMinLimitPeople;
    private int waitReservationMaxLimitPeople;

    @NotBlank(message = "입점 구분값을 선택해주세요.(실시간웨이팅, 예약)")
    private String enterShopType;

    private int reservationHourInterval;
    private int reservationMinuteInterval;

    @Min(0)
    private int waitAmLimitTeam;
    private int waitPmLimitTeam;


}
