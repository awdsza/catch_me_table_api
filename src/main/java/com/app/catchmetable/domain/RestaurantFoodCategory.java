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
}) // DB에서 사용하는 에티블명 명시, 그리고 해당 테이블의 제약조건을 추가해줄수 있다. 여기서는 Unique 제약조건을 추가한 상태,
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantFoodCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="restaurant_food_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 매핑( 레스토랑과 레스토랑 카테고리는 다대일 매핑) fetchType을 LAZY로 설정함으로써 레스토랑 정보를 가져올때 레스토랑 정보만 일단 가져오게
    @JoinColumn(name="restaurant_id") // 해당 엔티티를 관리하는 칼럼명 설정 하지않으면 중간 테이블 생성,
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
