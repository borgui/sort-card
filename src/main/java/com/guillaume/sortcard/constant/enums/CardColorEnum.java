package com.guillaume.sortcard.constant.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CardColorEnum {
    DIAMONDS("Diamonds"), SPADES("Spades"),
    HEARTS("Hearts"), CLUBS("Clubs");


    private String value;

    CardColorEnum(String value){
        this.value = value;
    }

    public static CardColorEnum getByValue(String value){
        if(value == null){
            return null;
        }
        return Arrays.stream(CardColorEnum.values()).filter(cardColorEnum -> cardColorEnum.getValue().equalsIgnoreCase(value)).findFirst().orElse(null);
    }
}
