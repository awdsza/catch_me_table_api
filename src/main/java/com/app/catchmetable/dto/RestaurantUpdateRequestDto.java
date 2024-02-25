package com.app.catchmetable.dto;

import com.app.catchmetable.domain.CloseState;
import com.app.catchmetable.domain.EnterShopType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantUpdateRequestDto {
    private String restaurantName;
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
    private String openTime;

    @JsonFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9])",message = "시간은 시:분 방식으로 입력해주세요.")
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
