package com.app.catchmetable.domain;

import com.app.catchmetable.dto.RestaurantUpdateRequestDto;
import com.app.catchmetable.dto.RestaurantRequestDto;
import com.app.catchmetable.utils.StringUtils;
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
@Setter(AccessLevel.PRIVATE)
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

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
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
    private boolean hasBreakTime;
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

    private Restaurant(String restaurantNumber, String userPW, String restaurantName, String restaurantAddress, String restaurantTelephoneNumber, String restaurantIntroduce ,Boolean hasBreakTime, Integer foodMinPrice, Integer foodMaxPrice, Integer restaurantLimitPeople, LocalTime openTime, LocalTime closeTime, LocalTime waitReservationStartTime, LocalTime waitReservationEndTime, Integer waitReservationMinLimitPeople, Integer waitReservationMaxLimitPeople, EnterShopType enterShopType) {
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
        if(foodMaxPrice != null){
            this.foodMaxPrice = foodMaxPrice;
        }
        this.restaurantLimitPeople = restaurantLimitPeople;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.waitReservationStartTime = waitReservationStartTime;
        this.waitReservationEndTime = waitReservationEndTime;
        this.waitReservationMinLimitPeople = waitReservationMinLimitPeople;
        this.waitReservationMaxLimitPeople = waitReservationMaxLimitPeople;
        this.enterShopType = enterShopType;
    }
    static public void updateRestaurant(Restaurant restaurant, RestaurantUpdateRequestDto dto){
        if(!StringUtils.isBlank(dto.getRestaurantName())){
            restaurant.setRestaurantName(dto.getRestaurantName());
        }
        if(!StringUtils.isBlank(dto.getRestaurantAddress())){
            restaurant.setRestaurantAddress(dto.getRestaurantAddress());
        }
        if(!StringUtils.isBlank(dto.getRestaurantIntroduce())){
            restaurant.setRestaurantIntroduce(dto.getRestaurantIntroduce());
        }

        if(!StringUtils.isBlank(dto.getOpenTime())){
            restaurant.setOpenTime(LocalTime.parse(dto.getOpenTime()));
        }
        if(!StringUtils.isBlank(dto.getCloseTime())){
            restaurant.setCloseTime(LocalTime.parse(dto.getCloseTime()));
        }
        restaurant.setCloseState(dto.getCloseState() == null ? CloseState.N : dto.getCloseState() );
        if(dto.getRestaurantFoodCategoryList().size() > 0){
            List<RestaurantFoodCategory> restaurantFoodCategoryList = restaurant.getRestaurantFoodCategoryList();
            restaurantFoodCategoryList.clear();
            restaurant.addRestaurantFoodCategory(dto.getRestaurantFoodCategoryList());
        }
        if(dto.getFoodMinPrice() != null){
            restaurant.setFoodMinPrice(dto.getFoodMinPrice());
        }
        if(dto.getFoodMaxPrice() != null){
            restaurant.setFoodMaxPrice(dto.getFoodMaxPrice());
        }
        if(dto.getRestaurantLimitPeople() != null){
            restaurant.setRestaurantLimitPeople(dto.getRestaurantLimitPeople());
        }
        if(dto.getHasBreakTime() != null){
            restaurant.setHasBreakTime(dto.getHasBreakTime());
        }
        if(!StringUtils.isBlank(dto.getBreakStartTime())){
            restaurant.setBreakStartTime(LocalTime.parse(dto.getBreakStartTime()));
        }
        if(!StringUtils.isBlank(dto.getBreakEndTime())){
            restaurant.setBreakEndTime(LocalTime.parse(dto.getBreakEndTime()));
        }
        if(dto.getEnterShopType() != null){
            restaurant.setEnterShopType(dto.getEnterShopType());
        }
        if(!StringUtils.isBlank(dto.getWaitReservationStartTime())){
            restaurant.setWaitReservationStartTime(LocalTime.parse(dto.getWaitReservationStartTime()));
        }
        if(!StringUtils.isBlank(dto.getWaitReservationEndTime())){
            restaurant.setWaitReservationEndTime(LocalTime.parse(dto.getWaitReservationEndTime()));
        }
        if(dto.getWaitReservationMinLimitPeople() != null){
            restaurant.setWaitReservationMinLimitPeople(dto.getWaitReservationMinLimitPeople());
        }
        if(dto.getWaitReservationMaxLimitPeople() != null){
            restaurant.setWaitReservationMaxLimitPeople(dto.getWaitReservationMaxLimitPeople());
        }

        if(dto.getReservationMinuteInterval() != null){
            restaurant.setReservationMinuteInterval(dto.getReservationMinuteInterval());
        }
        if(dto.getReservationHourInterval() != null){
            restaurant.setReservationHourInterval(dto.getReservationHourInterval());
        }

        if(dto.getWaitAmLimitTeam() != null){
            restaurant.setWaitAmLimitTeam(dto.getWaitAmLimitTeam());
        }
        if(dto.getWaitPmLimitTeam() != null){
            restaurant.setWaitPmLimitTeam(dto.getWaitPmLimitTeam());
        }

    }
    static public Restaurant createRestaurant(RestaurantRequestDto dto){

        LocalTime openTime = LocalTime.parse(dto.getOpenTime());
        LocalTime closeTime = LocalTime.parse(dto.getCloseTime());

        EnterShopType shopType = dto.getEnterShopType();
        String waitReservationStartTimeStr = dto.getWaitReservationStartTime();
        LocalTime waitReservationStartTime = StringUtils.isBlank(waitReservationStartTimeStr) ? openTime : LocalTime.parse(waitReservationStartTimeStr);
        String waitReservationEndTimeStr = dto.getWaitReservationEndTime();
        LocalTime waitReservationEndTime = StringUtils.isBlank(waitReservationEndTimeStr) ? closeTime : LocalTime.parse(waitReservationEndTimeStr);
        Boolean hasBreakTime = dto.getHasBreakTime();

        Restaurant restaurant = new Restaurant(dto.getRestaurantNumber(), dto.getUserPW(), dto.getRestaurantName(), dto.getRestaurantAddress(), dto.getRestaurantTelephoneNumber(), dto.getRestaurantIntroduce(),
             dto.getHasBreakTime(), dto.getFoodMinPrice(), dto.getFoodMaxPrice(), dto.getRestaurantLimitPeople(), openTime, closeTime, waitReservationStartTime, waitReservationEndTime,dto.getWaitReservationMinLimitPeople(),dto.getWaitReservationMaxLimitPeople(), shopType);
        if(hasBreakTime && ( !StringUtils.isBlank(dto.getBreakStartTime()) && StringUtils.isBlank(dto.getBreakStartTime()))){

            restaurant.setBreakStartTime(LocalTime.parse(dto.getBreakStartTime()));
            restaurant.setBreakEndTime(LocalTime.parse(dto.getBreakEndTime()));
        }
        if(shopType == EnterShopType.RESERVATION){
            restaurant.setReservationHourInterval(dto.getReservationHourInterval());
            restaurant.setReservationMinuteInterval(dto.getReservationMinuteInterval());
        }else{
            Integer waitAmLimitTeam = dto.getWaitAmLimitTeam();
            Integer waitPmLimitTeam = dto.getWaitPmLimitTeam();
            if(waitAmLimitTeam != null){
                restaurant.setWaitAmLimitTeam(waitAmLimitTeam);
            }
            if(waitPmLimitTeam != null){
                restaurant.setWaitPmLimitTeam(waitPmLimitTeam);
            }
        }
        return restaurant;
    }
}
