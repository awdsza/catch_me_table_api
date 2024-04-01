package com.app.catchmetable.service;

import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.dto.*;
import com.app.catchmetable.exception.DuplicateUserException;
import com.app.catchmetable.exception.LoginFailException;
import com.app.catchmetable.exception.NotExistUserException;
import com.app.catchmetable.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    final private RestaurantRepository restaurantRepository;
    final private PasswordEncoder passwordEncoder;
    @Transactional
    public Long createRestaurant(RestaurantRequestDto registDto){
        restaurantRepository.findByUserName(registDto.getRestaurantNumber())
                .orElseThrow(()-> new DuplicateUserException("이미 등록된 레스토랑 입니다."));
        registDto.changeEncodePw(passwordEncoder.encode(registDto.getUserPw()));
        Restaurant restaurant = Restaurant.createRestaurant(registDto);//레스토랑 추가
        Long id = restaurantRepository.save(restaurant);
        restaurant.addRestaurantFoodCategory(registDto.getRestaurantFoodCategoryList());//레스토랑에 음식 카테고리 추가.
        return id;
    }

    public RestaurantDto login(LoginDto loginDto){
        return restaurantRepository.findByUserName(loginDto.getUserId())
                .filter(restaurant-> passwordEncoder.matches(loginDto.getUserPw(),restaurant.getUserPw()))
                .map(RestaurantDto::createSuccessDto)
                .orElseThrow(()-> new LoginFailException("사용자가 없거나 로그인 정보가 틀린 대상입니다."));
    }
    public Restaurant findRestaurant(Long restaurantId){
        return restaurantRepository.findRestaurant(restaurantId)
                .orElseThrow(()-> new NotExistUserException("해당 ID를 가진 레스토랑이 존재하지않습니다."));
    }

    @Transactional
    public void updateRestaurant(Long restaurantId, RestaurantUpdateRequestDto updateDto){
        findRestaurant(restaurantId).updateRestaurant(updateDto);
    }
    @Transactional
    public void updateUserPw(Long restaurantId, RestaurantUpdateRequestDto updateDto){
        String encodePw = passwordEncoder.encode(updateDto.getUserPw());
        findRestaurant(restaurantId).updateRestaurantPassword(encodePw);

    }
    @Transactional
    public void deleteRestaurant(Long restaurantId){
        findRestaurant(restaurantId).deleteRestaurant();
    }

}
