package com.app.catchmetable.repository;

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

    public void save(Restaurant restaurant){
        entityManager.persist(restaurant);
    }
    public List<User> isDuplicateUser(String userID){
        return entityManager.createQuery("SELECT u FROM User u where u.userId=:userID",User.class)
                .setParameter("userID",userID)
                .getResultList();
    }
    public List<Restaurant> findRestaurant(LoginDto loginDto){
        TypedQuery<Restaurant> query = entityManager.createQuery("select r from Restaurant r LEFT JOIN " +
                "RestaurantFoodCategory rfx on r.id = rfx.id where r.userId=:userID AND r.userPw=:userPW", Restaurant.class);
        query
                .setParameter("userID", loginDto.getUserId())
                .setParameter("userPW", loginDto.getUserPw());
        return query.getResultList();
    }
}
