package com.app.catchmetable.domain;

import lombok.Getter;

public enum EnterShopType {
    RESERVATION("예약"),WAITING("웨이팅");

    private final String type_name;

    EnterShopType(String type_name) {
        this.type_name = type_name;
    }
}
