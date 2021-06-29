package com.guillaume.sortcard.model;


import lombok.Data;

import java.util.List;

@Data
public class CardSortRequest {

    List<String> colorOrder;
    List<String> valueOrder;
    List<Card> cards;
}
