package com.app.catchmetable.dto;

import com.app.catchmetable.domain.CloseState;
import com.app.catchmetable.domain.EnterShopType;
import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.domain.RestaurantFoodCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class LoginSuccessDto {
    private String userId;
    private String restaurantNumber;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantTelephoneNumber;
    private String restaurantIntroduce;

    private CloseState closeState;

    private List<RestaurantFoodCategory> restaurantFoodCategoryList = new ArrayList<>();

    private int foodMinPrice;
    private int foodMaxPrice;

    private int restaurantLimitPeople;

    private LocalTime openTime;
    private LocalTime closeTime;

    private boolean hasBreakTime;
    private LocalTime breakStartTime;
    private LocalTime breakEndTime;

    private EnterShopType enterShopType;

    private LocalTime waitReservationStartTime;
    private LocalTime waitReservationEndTime;
    private int waitReservationMinLimitPeople;
    private int waitReservationMaxLimitPeople;

    private int reservationHourInterval;
    private int reservationMinuteInterval;

    private int waitAmLimitTeam;
    private int waitPmLimitTeam;

    private LoginSuccessDto(Restaurant restaurant) {
        this.userId = restaurant.getUserId();
        this.restaurantNumber = restaurant.getRestaurantNumber();
        this.restaurantName = restaurant.getRestaurantName();
        this.restaurantAddress = restaurant.getRestaurantAddress();
        this.restaurantTelephoneNumber = restaurant.getRestaurantTelephoneNumber();
        this.restaurantIntroduce = restaurant.getRestaurantIntroduce();
        this.closeState = restaurant.getCloseState();
        this.restaurantFoodCategoryList = restaurant.getRestaurantFoodCategoryList();
        this.foodMinPrice = restaurant.getFoodMinPrice();
        this.foodMaxPrice = restaurant.getFoodMaxPrice();
        this.restaurantLimitPeople = restaurant.getRestaurantLimitPeople();
        this.openTime = restaurant.getOpenTime();
        this.closeTime = restaurant.getCloseTime();
        this.hasBreakTime = restaurant.isHasBreakTime();
        this.breakStartTime = restaurant.getBreakStartTime();
        this.breakEndTime = restaurant.getBreakEndTime();
        this.enterShopType = restaurant.getEnterShopType();
        this.waitReservationStartTime = restaurant.getWaitReservationStartTime();
        this.waitReservationEndTime = restaurant.getWaitReservationEndTime();
        this.waitReservationMinLimitPeople = restaurant.getWaitReservationMinLimitPeople();
        this.waitReservationMaxLimitPeople = restaurant.getWaitReservationMaxLimitPeople();
        this.reservationHourInterval = restaurant.getReservationHourInterval();
        this.reservationMinuteInterval = restaurant.getReservationMinuteInterval();
        this.waitAmLimitTeam = restaurant.getWaitAmLimitTeam();
        this.waitPmLimitTeam = restaurant.getWaitPmLimitTeam();

    }

    public static LoginSuccessDto createSuccessDto(Restaurant restaurant){

        return new LoginSuccessDto(restaurant);
    }
}
