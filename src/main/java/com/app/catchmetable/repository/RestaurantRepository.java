package com.app.catchmetable.repository;

import com.app.catchmetable.domain.Restaurant;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager entityManager;

    public void save(Restaurant restaurant){
        entityManager.persist(restaurant);
    }
}
