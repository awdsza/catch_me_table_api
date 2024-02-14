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
    public int isDuplicateUser(String userID){
        return entityManager.createQuery("SELECT u FROM User u where u.userId=:userID",User.class)
                .setParameter("userID",userID)
                .getResultList().size();
    }
    public Restaurant findRestaurant(Long restuarant_id){
        return entityManager.find(Restaurant.class,restuarant_id);
    }
    public List<Restaurant> login(LoginDto loginDto){
        TypedQuery<Restaurant> query = entityManager.createQuery("select r from Restaurant r  where r.userId=:userID AND r.userPw=:userPW AND r.closeState='N'", Restaurant.class);
        query
                .setParameter("userID", loginDto.getUserId())
                .setParameter("userPW", loginDto.getUserPw());
        return query.getResultList();
    }
}
