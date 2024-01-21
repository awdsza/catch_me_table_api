package com.app.catchmetable.service;

import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.dto.RestaurantRegistRequestDto;
import com.app.catchmetable.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    final private RestaurantRepository repository;

    @Transactional
    public void createRestaurant(RestaurantRegistRequestDto registDto){
        Restaurant restaurant = Restaurant.createRestaurant(registDto);//레스토랑 추가
        repository.save(restaurant);
        restaurant.addRestaurantFoodCategory(registDto.getRestaurantFoodCategoryList());//레스토랑에 음식 카테고리 추가.
    }
}
