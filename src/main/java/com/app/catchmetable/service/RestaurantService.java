package com.app.catchmetable.service;

import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.dto.LoginDto;
import com.app.catchmetable.dto.RestaurantRegistRequestDto;
import com.app.catchmetable.exception.DuplicateRestaurantNumberException;
import com.app.catchmetable.exception.LoginFailException;
import com.app.catchmetable.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    final private RestaurantRepository repository;

    @Transactional
    public void createRestaurant(RestaurantRegistRequestDto registDto){
        if(isDuplicateID(registDto.getRestaurantNumber())){
            throw new DuplicateRestaurantNumberException("중복된 레스토랑 입니다.");
        }
        Restaurant restaurant = Restaurant.createRestaurant(registDto);//레스토랑 추가
        repository.save(restaurant);
        restaurant.addRestaurantFoodCategory(registDto.getRestaurantFoodCategoryList());//레스토랑에 음식 카테고리 추가.
    }
    private boolean isDuplicateID(String restaurantNumber){
        return repository.isDuplicateUser(restaurantNumber).size() > 0;
    }
    public Restaurant login(LoginDto loginDto){

        List<Restaurant> result =  repository.findRestaurant(loginDto);
        if(result == null || result.isEmpty()){
            throw new LoginFailException("사용자가 없거나 로그인 정보가 틀린 대상입니다.");
        }
        return result.get(0);
    }
}
