package com.app.catchmetable.service;

import com.app.catchmetable.domain.*;
import com.app.catchmetable.dto.*;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RestaurantServiceTest {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void 레스토랑_생성() {
        //given
        ArrayList<String> restaurantFootCategoryList = new ArrayList<>();
        restaurantFootCategoryList.add("KATSU");
        restaurantFootCategoryList.add("JAPANESE");
        RestaurantRequestDto registDto = new RestaurantRequestDto(
"5521300286",
                "1234",
                "시오톤",
                "대구광역시 수성구 수성로 341, 1층(수성동1가)",
                "050714127115",
                "흑돼지 돈까스가 맛있는곳!",
                CloseState.N,
                restaurantFootCategoryList,
                15000,
                23000,
                20,
                "11:30",
                "21:00",
                Boolean.TRUE,
                "14:40",
                "17:00",
                "11:00",
                "20:30",
                1,
                4,
                EnterShopType.RESERVATION,
                1,
                0,
                null,
                null
        );
        //when
        Long id = restaurantService.createRestaurant(registDto);
       //then
        assertNotNull(id);
    }

    @Test
    void 로그인() {
        //given
        LoginDto loginDto = new LoginDto(    "5521300286","1234");
        //when
        RestaurantDto login = restaurantService.login(loginDto);
        //then
        assertNotNull(login);
    }

    @Test
    void 레스토랑_정보검색() {
        //given
        Long restaurantId = 3L;
        //when
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        //then
        assertNotNull(restaurant);
    }

    @Test
    void 레스토랑_변경() {

        //given
        ArrayList<String> restaurantFootCategoryList = new ArrayList<>();
        restaurantFootCategoryList.add("JAPANESE");
        Long restaurantId = 3L;
        RestaurantUpdateRequestDto updateDto = new RestaurantUpdateRequestDto(null,null,"대구 중구 달구벌대로 2077 지하 1층"
                ,null,null,null
                ,restaurantFootCategoryList
                ,null
        ,null
        ,null
        ,"10:30"
        ,"20:00"
        , Boolean.FALSE
        , null
        ,null
        ,null
        ,null
        ,null
        ,null
        ,EnterShopType.WAITING
        ,null
        , null
        ,null
        ,null
        );
        //when
        restaurantService.updateRestaurant(restaurantId,updateDto);
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        //then
        assertEquals(restaurantFootCategoryList.size(),restaurant.getRestaurantFoodCategoryList().size());
    }
    @Test
    void 사용자_비밀번호_변경_테스트(){
        String rawPassword = "1234";
        RestaurantUpdateRequestDto customerUpdateRequestDto = RestaurantUpdateRequestDto.createUpdatePasswordRequestDto(rawPassword);
        Long restaurantId = 3L;
        //when
        restaurantService.updateUserPw(restaurantId,customerUpdateRequestDto);
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);

//        assertTrue(restaurant instanceof User);
//        Customer customer = customerService.findUser(customerId);
        assertTrue(passwordEncoder.matches(rawPassword,restaurant.getUserPw()));
    }
    @Test
    void deleteRestaurant() {
        //given
        Long restaurantId = 3L;
        //when
        restaurantService.deleteRestaurant(restaurantId);
        //then
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        assertEquals(restaurant.getIsUse(), UserState.N);
    }
}