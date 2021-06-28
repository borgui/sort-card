package com.guillaume.sortcard.controller;


import com.guillaume.sortcard.model.Card;
import com.guillaume.sortcard.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @GetMapping("/card/new")
    public ResponseEntity<List<Card>> getNewHand(@RequestParam("size") Integer size){

        try{
            List<Card> cards = cardService.generateNewHand(size);
            logger.info("Your hand:");
            cardService.printCards(cards);
            cardService.sortCards(cards);
            logger.info("------------------");
            logger.info("Your sort hand: ");
            cardService.printCards(cards);
            return ResponseEntity.ok(cards);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST);
        }


    }

    @PostConstruct
    public void generate10Hand(){
        this.getNewHand(10);
    }
}
