package com.guillaume.sortcard.service;

import com.guillaume.sortcard.constant.CardConstant;
import com.guillaume.sortcard.constant.ExceptionConstant;
import com.guillaume.sortcard.constant.enums.CardColorEnum;
import com.guillaume.sortcard.constant.enums.CardValueEnum;
import com.guillaume.sortcard.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CardService {

    private static final Random RANDOM = new Random();

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    /**
     * Generate a new random hand of cards of a given size
     * @param  size
     * @return List<cards>
     */
    public List<Card> generateNewHand(Integer size) throws Exception {
        if(size > CardConstant.MAX_HAND_SIZE){
            throw new Exception(ExceptionConstant.SIZE_OVER_TOTAL_COMBINATION);
        }
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i < size; i++){
            boolean uniqueValue = false;
            while(!uniqueValue){
                Card generatedCard = generateCard();
                if(cards.stream().filter(card -> card.getValue().equals(generatedCard.getValue())
                        && card.getColor().equals(generatedCard.getColor())).findAny().orElse(null) == null){
                    cards.add(generatedCard);
                    uniqueValue = true;
                };
            }

        }
        return cards;
    }

    /**
     * Get random order of color enum
     * @return
     */
    public List<CardValueEnum> getRandomValueOrder(){
        List<CardValueEnum> valueEnums = Arrays.asList(CardValueEnum.values());
        Collections.shuffle(valueEnums);
        return valueEnums;
    }

    /**
     * Get d order of color enum
     * @return
     */
    public List<CardColorEnum> getRandomColorOrder(){
        List<CardColorEnum> colorEnums = Arrays.asList(CardColorEnum.values());
        Collections.shuffle(colorEnums);
        return colorEnums;
    }

    /**
     * Generate a new random card
     * @return card
     */
    public Card generateCard(){
        return new Card(CardValueEnum.values()[getNextRandom(CardValueEnum.values().length)].getValue(),
                CardColorEnum.values()[getNextRandom(CardColorEnum.values().length)].getValue());
    }

    public Integer getNextRandom(Integer length){
        return RANDOM.nextInt(length - 1);
    }

    /**
     * Print out informations of all cards
     * @param cards
     */
    public void printCards(List<Card> cards){
        cards.forEach(card -> {
            logger.info(card.getValue() + " " + card.getColor());
        });

    }

    /**
     * Sort cards by value and color
     * @param cards
     */
    public void sortCards(List<Card> cards, List<String> valueOrder, List<String> colorOrder){
        Comparator<Card> comparator = Comparator.comparingInt(a -> valueOrder.indexOf(a.getValue()));
        comparator = comparator.thenComparingInt(a -> colorOrder.indexOf(a.getColor()));
        cards.sort(comparator);
    }

}
