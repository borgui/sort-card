package com.guillaume.sortcard.constant.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum CardValueEnum {
    TWO("2"), THREE("3"),
    FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), TEN("10"), JACK("JACK"),
    QUEEN("QUEEN"), KING("KING"), AS("AS");

    private String value;

    CardValueEnum(String value){
        this.value = value;
    }

    public static CardValueEnum getByValue(String value){
        if(value == null){
            return null;
        }
        return Arrays.stream(CardValueEnum.values()).filter(cardValueEnum -> cardValueEnum.getValue().equalsIgnoreCase(value)).findFirst().orElse(null);
    }
}
