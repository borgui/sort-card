package com.guillaume.sortcard.constant.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum CardValueEnum {
    TWO("2", 1), THREE("3", 2),
    FOUR("4", 3), FIVE("5", 4), SIX("6", 5), SEVEN("7", 6),
    EIGHT("8", 7), NINE("9", 8), TEN("10", 9), JACK("JACK", 10),
    QUEEN("QUEEN", 11), KING("KING", 12), AS("AS", 13);

    private String value;

    private Integer order;

    CardValueEnum(String value, Integer order){
        this.value = value;
        this.order = order;
    }

    public static CardValueEnum getByValue(String value){
        if(value == null){
            return null;
        }
        return Arrays.stream(CardValueEnum.values()).filter(cardValueEnum -> cardValueEnum.getValue().equalsIgnoreCase(value)).findFirst().orElse(null);
    }
}
