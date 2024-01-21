package com.app.catchmetable.domain;

import com.app.catchmetable.dto.RestaurantRegistRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "restaurants",uniqueConstraints = {
        @UniqueConstraint(name="unique_restaurant"
                ,columnNames={"restaurant_id","restaurant_number"})
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;
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
    private boolean isCloseYn;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantFoodCategory> restaurantFoodCategoryList = new ArrayList<>();

    @Column(name="food_min_price")
    private int foodMinPrice;
    @Column(name="food_max_price")
    private int foodMaxPrice;

    @Column(name="open_time")
    private LocalTime openTime;
    @Column(name="close_time")
    private LocalTime closeTime;

    @Column(name="has_break_time")
    boolean hasBreakTime;
    @Column(name="break_start_time")
    private LocalTime breakStartTime;
    @Column(name="break_end_time")
    private LocalTime breakEndTime;
    @Column(name="wait_reservation_start_time")
    private LocalTime waitReservationStartTime;
    @Column(name="wait_reservation_end_time")
    private LocalTime waitReservationEndTime;
    @Column(name="wait_reservation_min_limit_people")
    private int waitReservationMinLimitPeople;
    @Column(name="wait_reservation_max_limit_people")
    private int waitReservationMaxLimitPeople;

    @Column(name="enter_shop_type")
    @Enumerated(EnumType.STRING)
    private EnterShopType enterShopType;

    @Column(name="reservation_hour_interval")
    private int reservationHourInterval;
    @Column(name="reservation_minute_interval")
    private int reservationMinuteInterval;

    @Column(name="wait_am_limit_team")
    private int waitAmLimitTeam;
    @Column(name="wait_pm_limit_team")
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
    static public Restaurant createRestaurant(RestaurantRegistRequestDto dto){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantNumber(dto.getRestaurantNumber());
        restaurant.setRestaurantName(dto.getRestaurantName());
        restaurant.setRestaurantAddress(dto.getRestaurantAddress());
        restaurant.setRestaurantTelephoneNumber(dto.getRestaurantTelephoneNumber());
        restaurant.setRestaurantIntroduce(dto.getRestaurantIntroduce());



        restaurant.setFoodMinPrice(dto.getFoodMinPrice());
        restaurant.setFoodMinPrice(dto.getFoodMaxPrice());

        restaurant.setOpenTime(dto.getOpenTime());
        restaurant.setCloseTime(dto.getCloseTime());

        restaurant.setHasBreakTime(dto.isHasBreakTime());
        restaurant.setBreakStartTime(dto.getBreakStartTime());
        restaurant.setBreakEndTime(dto.getBreakEndTime());

        restaurant.setWaitReservationStartTime(dto.getWaitReservationStartTime());
        restaurant.setWaitReservationEndTime(dto.getWaitReservationEndTime());

        restaurant.setWaitReservationMinLimitPeople(dto.getWaitReservationMinLimitPeople());
        restaurant.setWaitReservationMaxLimitPeople(dto.getWaitReservationMaxLimitPeople());

        if(restaurant.getEnterShopType() == EnterShopType.RESERVATION){
            restaurant.setReservationHourInterval(dto.getReservationHourInterval());
            restaurant.setReservationMinuteInterval(dto.getReservationMinuteInterval());
        }else{
            restaurant.setWaitAmLimitTeam(dto.getWaitAmLimitTeam());
            restaurant.setWaitPmLimitTeam(dto.getWaitPmLimitTeam());
        }
        return restaurant;
    }
}
