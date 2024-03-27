package com.app.catchmetable.repository;

import com.app.catchmetable.domain.Customer;
import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.domain.User;
import com.app.catchmetable.dto.LoginDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository{
    private final EntityManager entityManager;

    public <T extends User> Long save(T user) {
        entityManager.persist(user);
        return user.getId();
    }

    public Optional<Customer> findByUserName(String userID) {
        return Optional.of(entityManager.createQuery("SELECT u FROM Customer u where u.userId=:userID",Customer.class)
        .setParameter("userID",userID)
        .getSingleResult());
    }

    public Optional<Customer> findUser(Long user_id) {
        return Optional.of(entityManager.find(Customer.class,user_id));
    }
}
