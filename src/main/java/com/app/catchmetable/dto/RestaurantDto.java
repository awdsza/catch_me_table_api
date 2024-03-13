package com.app.catchmetable.dto;

import com.app.catchmetable.domain.EnterShopType;
import com.app.catchmetable.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class RestaurantDto implements Serializable {
    private String userId;
    private String restaurantNumber;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantTelephoneNumber;
    private String restaurantIntroduce;

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

    private RestaurantDto(Restaurant restaurant) {
        this.userId = restaurant.getUserId();
        this.restaurantNumber = restaurant.getRestaurantNumber();
        this.restaurantName = restaurant.getRestaurantName();
        this.restaurantAddress = restaurant.getRestaurantAddress();
        this.restaurantTelephoneNumber = restaurant.getRestaurantTelephoneNumber();
        this.restaurantIntroduce = restaurant.getRestaurantIntroduce();
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

    public static RestaurantDto createSuccessDto(Restaurant restaurant){

        return new RestaurantDto(restaurant);
    }
}
