package com.app.catchmetable.service;

import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.dto.LoginDto;
import com.app.catchmetable.dto.RestaurantRequestDto;
import com.app.catchmetable.exception.DuplicateRestaurantNumberException;
import com.app.catchmetable.exception.LoginFailException;
import com.app.catchmetable.exception.NotExistRestaurantException;
import com.app.catchmetable.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    final private RestaurantRepository repository;

    @Transactional
    public void createRestaurant(RestaurantRequestDto registDto){
        if(isDuplicateID(registDto.getRestaurantNumber())){
            throw new DuplicateRestaurantNumberException("중복된 레스토랑 입니다.");
        }
        Restaurant restaurant = Restaurant.createRestaurant(registDto);//레스토랑 추가
        repository.save(restaurant);
        restaurant.addRestaurantFoodCategory(registDto.getRestaurantFoodCategoryList());//레스토랑에 음식 카테고리 추가.
    }
    private boolean isDuplicateID(String restaurantNumber){
        return repository.isDuplicateUser(restaurantNumber) != 0;
    }

    public Restaurant login(LoginDto loginDto){

        List<Restaurant> result =  repository.login(loginDto);
        if(result == null || result.isEmpty()){
            throw new LoginFailException("사용자가 없거나 로그인 정보가 틀린 대상입니다.");
        }
        return result.get(0);
    }
    public Restaurant findRestaurant(Long restaurant_id){
        Restaurant restaurant = repository.findRestaurant(restaurant_id);
        if(restaurant == null){
            throw new NotExistRestaurantException("해당 ID를 가진 레스토랑이 존재하지않습니다.");
        }
        return restaurant;
    }
    @Transactional
    public void updateRestaurant(Long restaurant_id, RestaurantRequestDto updateDto){
        Restaurant restaurant = findRestaurant(restaurant_id);
        Restaurant.updateRestaurant(restaurant,updateDto);

        if(updateDto.getRestaurantFoodCategoryList() != null && updateDto.getRestaurantFoodCategoryList().size() > 0){
            restaurant.addRestaurantFoodCategory(updateDto.getRestaurantFoodCategoryList());
        }
    }
}
