package com.guillaume.sortcard.constant.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CardColorEnum {
    DIAMONDS("Diamonds", 1), SPADES("Spades", 2),
    HEARTS("Hearts", 3), CLUBS("Clubs", 4);


    private String value;
    private Integer order;

    CardColorEnum(String value, Integer order){
        this.value = value;
        this.order = order;
    }

    public static CardColorEnum getByValue(String value){
        if(value == null){
            return null;
        }
        return Arrays.stream(CardColorEnum.values()).filter(cardColorEnum -> cardColorEnum.getValue().equalsIgnoreCase(value)).findFirst().orElse(null);
    }
}
