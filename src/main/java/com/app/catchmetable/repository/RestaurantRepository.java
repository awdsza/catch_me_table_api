package com.app.catchmetable.repository;

import com.app.catchmetable.domain.Customer;
import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.domain.User;
import com.app.catchmetable.dto.LoginDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager entityManager;


    public Long save(Restaurant restaurant){
        entityManager.persist(restaurant);
        return restaurant.getId();
    }

    public Optional<Restaurant> findByUserName(String userID) {
        return Optional.of(entityManager.createQuery("SELECT u FROM Restaurant u where u.userId=:userID",Restaurant.class)
                .setParameter("userID",userID)
                .getSingleResult());
    }

    public Optional<Restaurant> findRestaurant(Long restaurantId){
        return Optional.of(entityManager.find(Restaurant.class,restaurantId));
    }
}
