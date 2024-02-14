package com.app.catchmetable.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="restaurant_food_category",uniqueConstraints = {
        @UniqueConstraint(name="unique_restaurant_food_category"
                ,columnNames={"restaurant_food_category_id","restaurant_id"})
})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantFoodCategory {
    @Id
    @GeneratedValue
    @Column(name="restaurant_food_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    @Setter(AccessLevel.PRIVATE)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.PRIVATE)
    private FoodCategory foodCategory;

    public static RestaurantFoodCategory createRestaurantFoodCategory(Restaurant restaurant,FoodCategory foodCategory){
        RestaurantFoodCategory restaurantFoodCategory = new RestaurantFoodCategory();
        restaurantFoodCategory.setRestaurant(restaurant);
        restaurantFoodCategory.setFoodCategory(foodCategory);
        return restaurantFoodCategory;
    }

}
