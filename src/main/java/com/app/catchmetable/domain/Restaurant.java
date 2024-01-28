package com.app.catchmetable.domain;

import com.app.catchmetable.dto.RestaurantRegistRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Table(name = "restaurants")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value="restaurant")
public class Restaurant extends User{
    @Column(name="restaurant_number")
    private String restaurantNumber;
    @Column(name="restaurant_name")
    private String restaurantName;
    @Column(name="restaurant_address")
    private String restaurantAddress;
    @Column(name="restaurant_telephone_number")
    private String restaurantTelephoneNumber;
    @Column(name="restaurant_introduce")
    private String restaurantIntroduce;

    @Column(name="is_close_yn")
    @Enumerated(EnumType.STRING)
    private CloseState closeState;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantFoodCategory> restaurantFoodCategoryList = new ArrayList<>();

    @Column(name="food_min_price")
    private int foodMinPrice;
    @Column(name="food_max_price")
    private int foodMaxPrice;

    @Column(name="restaurant_limit_people")
    private int restaurantLimitPeople;

    @Column(name="open_time")
    private LocalTime openTime;
    @Column(name="close_time")
    private LocalTime closeTime;

    @Column(name="has_break_time")
    @Setter(AccessLevel.PRIVATE)
    boolean hasBreakTime;
    @Column(name="break_start_time")
    @Setter(AccessLevel.PRIVATE)
    private LocalTime breakStartTime;
    @Column(name="break_end_time")
    @Setter(AccessLevel.PRIVATE)
    private LocalTime breakEndTime;

    @Column(name="enter_shop_type")
    @Enumerated(EnumType.STRING)
    private EnterShopType enterShopType;

    @Column(name="wait_reservation_start_time")
    private LocalTime waitReservationStartTime;
    @Column(name="wait_reservation_end_time")
    private LocalTime waitReservationEndTime;
    @Column(name="wait_reservation_min_limit_people")
    private int waitReservationMinLimitPeople;
    @Column(name="wait_reservation_max_limit_people")
    private int waitReservationMaxLimitPeople;



    @Column(name="reservation_hour_interval")
    @Setter(AccessLevel.PRIVATE)
    private int reservationHourInterval;
    @Column(name="reservation_minute_interval")
    @Setter(AccessLevel.PRIVATE)
    private int reservationMinuteInterval;

    @Column(name="wait_am_limit_team")
    @Setter(AccessLevel.PRIVATE)
    private int waitAmLimitTeam;
    @Column(name="wait_pm_limit_team")
    @Setter(AccessLevel.PRIVATE)
    private int waitPmLimitTeam;


    public void addRestaurantFoodCategory(List<String> categoryList){
        for(String categoryString:categoryList){
            for(FoodCategory value : FoodCategory.values()){
                if(value.name().equals(categoryString)){
                    this.getRestaurantFoodCategoryList().add(RestaurantFoodCategory.createRestaurantFoodCategory(this,value));
                    break;
                }
            }
        }
    }

    private Restaurant(String restaurantNumber, String userPW, String restaurantName, String restaurantAddress, String restaurantTelephoneNumber, String restaurantIntroduce,boolean hasBreakTime, int foodMinPrice, int foodMaxPrice, int restaurantLimitPeople, LocalTime openTime, LocalTime closeTime, LocalTime waitReservationStartTime, LocalTime waitReservationEndTime, int waitReservationMinLimitPeople, int waitReservationMaxLimitPeople, EnterShopType enterShopType) {
        super(restaurantNumber,userPW,UserState.Y);
        //사용자 공통 엔티티 정보 생성.
        this.closeState = CloseState.N;
        this.restaurantNumber = restaurantNumber;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantTelephoneNumber = restaurantTelephoneNumber;
        this.restaurantIntroduce = restaurantIntroduce;
        this.hasBreakTime = hasBreakTime;
        this.foodMinPrice = foodMinPrice;
        this.foodMaxPrice = foodMaxPrice;
        this.restaurantLimitPeople = restaurantLimitPeople;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.waitReservationStartTime = waitReservationStartTime;
        this.waitReservationEndTime = waitReservationEndTime;
        this.waitReservationMinLimitPeople = waitReservationMinLimitPeople;
        this.waitReservationMaxLimitPeople = waitReservationMaxLimitPeople;
        this.enterShopType = enterShopType;


    }

    static public Restaurant createRestaurant(RestaurantRegistRequestDto dto){

        LocalTime openTime = LocalTime.parse(dto.getOpenTime());
        LocalTime closeTime = LocalTime.parse(dto.getCloseTime());

        EnterShopType shopType= null;
        for(EnterShopType value : EnterShopType.values()){
            if(value.name().equals(dto.getEnterShopType())){
                shopType = value;
                break;
            }
        }
        if(shopType == null){
            throw new IllegalArgumentException("잘못된 레스토랑 유형의 값이 들어갔습니다.");
        }
        String waitReservationStartTimeStr = dto.getWaitReservationStartTime();
        LocalTime waitReservationStartTime = waitReservationStartTimeStr == null || waitReservationStartTimeStr.isBlank() ? openTime : LocalTime.parse(waitReservationStartTimeStr);
        String waitReservationEndTimeStr = dto.getWaitReservationEndTime();
        LocalTime waitReservationEndTime = waitReservationEndTimeStr == null || waitReservationEndTimeStr.isBlank() ? closeTime : LocalTime.parse(waitReservationEndTimeStr);
        boolean hasBreakTime = dto.isHasBreakTime();

        Restaurant restaurant = new Restaurant(dto.getRestaurantNumber(), dto.getUserPW(), dto.getRestaurantName(), dto.getRestaurantAddress(), dto.getRestaurantTelephoneNumber(), dto.getRestaurantIntroduce(),
             dto.isHasBreakTime(), dto.getFoodMinPrice(), dto.getFoodMaxPrice(), dto.getRestaurantLimitPeople(), openTime, closeTime, waitReservationStartTime, waitReservationEndTime,dto.getWaitReservationMinLimitPeople(),dto.getWaitReservationMaxLimitPeople(), shopType);
        if(hasBreakTime && ( dto.getBreakStartTime() != null && dto.getBreakEndTime() != null && !dto.getBreakStartTime().isBlank() && !dto.getBreakEndTime().isBlank())){

            restaurant.setBreakStartTime(LocalTime.parse(dto.getBreakStartTime()));
            restaurant.setBreakEndTime(LocalTime.parse(dto.getBreakEndTime()));
        }



        if(shopType == EnterShopType.RESERVATION){
            restaurant.setReservationHourInterval(dto.getReservationHourInterval());
            restaurant.setReservationMinuteInterval(dto.getReservationMinuteInterval());
        }else{
            restaurant.setWaitAmLimitTeam(dto.getWaitAmLimitTeam());
            restaurant.setWaitPmLimitTeam(dto.getWaitPmLimitTeam());
        }
        return restaurant;
    }
}
