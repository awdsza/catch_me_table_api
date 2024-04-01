package com.app.catchmetable.domain;

import com.app.catchmetable.dto.CustomerUpdateRequestDto;
import com.app.catchmetable.dto.RestaurantUpdateRequestDto;
import com.app.catchmetable.dto.RestaurantRequestDto;
import com.app.catchmetable.utils.StringUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity // 해당 객체가 Entity임을 선언.
@Getter // Getter 자동 생성
@Table(name = "restaurants") // DB에서 사용할 테이블명 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 자동생성 접근자는 Protected 설정
@DiscriminatorValue(value="restaurant") // User 엔티티를 상속받은 엔티티의 구분값 설정.
@Setter(AccessLevel.PRIVATE) // Setter자동 생성. 접근자는 Private
public class Restaurant extends User{
    @Column(name="restaurant_number") // 테이블 칼럼명 설정
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
    @Enumerated(EnumType.STRING) // 테이블에서는 EnumType이 없기때문에 해당 어노테이션을 이용하여 매칭. Enum의 문자열을 저장
    private CloseState closeState;

    /** 일대다 매칭 RestaurantFoodCategory엔티티의 restaurant 필드명과 매핑하겠다는 뜻
     * 레스토랑 엔티티를 불러올때 RestaurantFoodCategory도 불러온다면 RestaurantFoodCategory도 영속상태로 만들어좀.
     * 해당 엔티티는 고아객체 True 설정. Restaurant가 삭제되면 해당 엔티티도 삭제처리.
     */
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
    private boolean hasBreakTime;
    @Column(name="break_start_time")
    private LocalTime breakStartTime;
    @Column(name="break_end_time")
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
    public void deleteRestaurant(){
        this.deleteUser(this);
    }

    public void updateRestaurant(RestaurantUpdateRequestDto dto){
        if(!StringUtils.isBlank(dto.getRestaurantName())){
            this.setRestaurantName(dto.getRestaurantName());
        }
        if(!StringUtils.isBlank(dto.getRestaurantAddress())){
            this.setRestaurantAddress(dto.getRestaurantAddress());
        }
        if(!StringUtils.isBlank(dto.getRestaurantIntroduce())){
            this.setRestaurantIntroduce(dto.getRestaurantIntroduce());
        }

        if(!StringUtils.isBlank(dto.getOpenTime())){
            this.setOpenTime(LocalTime.parse(dto.getOpenTime()));
        }
        if(!StringUtils.isBlank(dto.getCloseTime())){
            this.setCloseTime(LocalTime.parse(dto.getCloseTime()));
        }
        this.setCloseState(dto.getCloseState() == null ? CloseState.N : dto.getCloseState() );
        if(dto.getRestaurantFoodCategoryList().size() > 0){
            List<RestaurantFoodCategory> restaurantFoodCategoryList = this.getRestaurantFoodCategoryList();
            restaurantFoodCategoryList.clear();
            this.addRestaurantFoodCategory(dto.getRestaurantFoodCategoryList());
        }
        if(dto.getFoodMinPrice() != null){
            this.setFoodMinPrice(dto.getFoodMinPrice());
        }
        if(dto.getFoodMaxPrice() != null){
            this.setFoodMaxPrice(dto.getFoodMaxPrice());
        }
        if(dto.getRestaurantLimitPeople() != null){
            this.setRestaurantLimitPeople(dto.getRestaurantLimitPeople());
        }
        if(dto.getHasBreakTime() != null){
            this.setHasBreakTime(dto.getHasBreakTime());
        }
        if(!StringUtils.isBlank(dto.getBreakStartTime())){
            this.setBreakStartTime(LocalTime.parse(dto.getBreakStartTime()));
        }
        if(!StringUtils.isBlank(dto.getBreakEndTime())){
            this.setBreakEndTime(LocalTime.parse(dto.getBreakEndTime()));
        }
        if(dto.getEnterShopType() != null){
            this.setEnterShopType(dto.getEnterShopType());
        }
        if(!StringUtils.isBlank(dto.getWaitReservationStartTime())){
            this.setWaitReservationStartTime(LocalTime.parse(dto.getWaitReservationStartTime()));
        }
        if(!StringUtils.isBlank(dto.getWaitReservationEndTime())){
            this.setWaitReservationEndTime(LocalTime.parse(dto.getWaitReservationEndTime()));
        }
        if(dto.getWaitReservationMinLimitPeople() != null){
            this.setWaitReservationMinLimitPeople(dto.getWaitReservationMinLimitPeople());
        }
        if(dto.getWaitReservationMaxLimitPeople() != null){
            this.setWaitReservationMaxLimitPeople(dto.getWaitReservationMaxLimitPeople());
        }

        if(dto.getReservationMinuteInterval() != null){
            this.setReservationMinuteInterval(dto.getReservationMinuteInterval());
        }
        if(dto.getReservationHourInterval() != null){
            this.setReservationHourInterval(dto.getReservationHourInterval());
        }

        if(dto.getWaitAmLimitTeam() != null){
            this.setWaitAmLimitTeam(dto.getWaitAmLimitTeam());
        }
        if(dto.getWaitPmLimitTeam() != null){
            this.setWaitPmLimitTeam(dto.getWaitPmLimitTeam());
        }

    }
    public void updateRestaurantPassword(String userPw){
        this.updateUserPw(userPw);
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

        Restaurant restaurant = new Restaurant(dto.getRestaurantNumber(), dto.getUserPw(), dto.getRestaurantName(), dto.getRestaurantAddress(), dto.getRestaurantTelephoneNumber(), dto.getRestaurantIntroduce(),
             dto.getHasBreakTime(), dto.getFoodMinPrice(), dto.getFoodMaxPrice(), dto.getRestaurantLimitPeople(), openTime, closeTime, waitReservationStartTime, waitReservationEndTime,dto.getWaitReservationMinLimitPeople(),dto.getWaitReservationMaxLimitPeople(), shopType);
        if(hasBreakTime && ( !StringUtils.isBlank(dto.getBreakStartTime()) && !StringUtils.isBlank(dto.getBreakEndTime()))){

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
