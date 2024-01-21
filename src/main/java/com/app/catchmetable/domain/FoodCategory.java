package com.app.catchmetable.domain;

import lombok.Getter;

import java.time.chrono.JapaneseEra;

@Getter
public enum FoodCategory {

    JAPANESE(null),
    KOREAN(null),
    PIZZA(null),
    PASTA(null),
    BRUNCH(null),
    DESSERT(null),
    ETC(null),
    OMACASE(JAPANESE),
    SUSHI(JAPANESE),
    KATSU(JAPANESE),
    RAMEN(JAPANESE),
    YAKITORI(JAPANESE)
    ;

    final private FoodCategory parentCategory;

    FoodCategory(FoodCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

}
