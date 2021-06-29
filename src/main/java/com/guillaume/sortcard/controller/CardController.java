package com.guillaume.sortcard.controller;


import com.guillaume.sortcard.constant.enums.CardColorEnum;
import com.guillaume.sortcard.constant.enums.CardValueEnum;
import com.guillaume.sortcard.model.Card;
import com.guillaume.sortcard.model.CardSortRequest;
import com.guillaume.sortcard.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @PostMapping("/card/new")
    public ResponseEntity<List<Card>> getNewHand(@RequestParam("size") Integer size){
        try{
            List<Card> cards = cardService.generateNewHand(size);
            logger.info("Your hand:");
            cardService.printCards(cards);
            return ResponseEntity.ok(cards);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/card/sort")
    public ResponseEntity<List<Card>> sortCard(@RequestBody CardSortRequest cardSortRequest){
        cardService.sortCards(cardSortRequest.getCards(), cardSortRequest.getValueOrder(), cardSortRequest.getColorOrder());
        logger.info("Your sort hand: ");
        cardService.printCards(cardSortRequest.getCards());
        return ResponseEntity.ok(cardSortRequest.getCards());
    }
    @GetMapping("/card/values/order")
    public ResponseEntity<List<CardValueEnum>> getRandomValueOrder() {
        return ResponseEntity.ok(cardService.getRandomValueOrder());
    }

    @GetMapping("/card/colors/order")
    public ResponseEntity<List<CardColorEnum>> getRandomColorOrder() {
        return ResponseEntity.ok(cardService.getRandomColorOrder());
    }

}
