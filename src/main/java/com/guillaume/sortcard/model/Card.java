package com.guillaume.sortcard.model;

import lombok.Data;

@Data
public class Card {
    private String value;
    private String color;

    public Card(String value, String color){
        this.value = value;
        this.color = color;
    }
}
