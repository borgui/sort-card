package com.guillaume.sortcard.service;

import com.guillaume.sortcard.constant.CardConstant;
import com.guillaume.sortcard.constant.ExceptionConstant;
import com.guillaume.sortcard.constant.enums.CardColorEnum;
import com.guillaume.sortcard.constant.enums.CardValueEnum;
import com.guillaume.sortcard.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration
class CardServiceTest {

    @SpyBean
    CardService cardService;

    @Test
    void generateNewHand_2_combination() {
        try{
            List<CardValueEnum> values = asList(CardValueEnum.values());
            List<CardColorEnum> colors = asList(CardColorEnum.values());

            when(cardService.getNextRandom(CardValueEnum.values().length)).thenReturn(values.indexOf(CardValueEnum.KING), values.indexOf(CardValueEnum.FIVE));
            when(cardService.getNextRandom(CardColorEnum.values().length)).thenReturn(colors.indexOf(CardColorEnum.DIAMONDS), colors.indexOf(CardColorEnum.SPADES));

            List<Card> cards = cardService.generateNewHand(2);
            assertEquals(2,
                    cards.size());
            assertEquals(CardValueEnum.KING.getValue(), cards.get(0).getValue());
            assertEquals(CardColorEnum.DIAMONDS.getValue(), cards.get(0).getColor());
            assertEquals(CardValueEnum.FIVE.getValue(), cards.get(1).getValue());
            assertEquals(CardColorEnum.SPADES.getValue(), cards.get(1).getColor());

        } catch (Exception e){
            fail();
        }
    }

    @Test
    void generateNewHand_maximum_handSize() {
        try{
            List<Card> cards = cardService.generateNewHand(CardConstant.MAX_HAND_SIZE);
            assertEquals(CardConstant.MAX_HAND_SIZE,
                    cards.size());
        } catch (Exception e){
            fail();
        }
    }

    @Test
    void generateNewHand_error_combination() {
        try{
            cardService.generateNewHand(CardConstant.MAX_HAND_SIZE + 1);
            fail();
        } catch (Exception e){
            assertEquals(ExceptionConstant.SIZE_OVER_TOTAL_COMBINATION, e.getMessage());
        }
    }


    @Test
    void sortCard_different_value() {
        List<Card> cards = Arrays.asList(new Card(CardValueEnum.EIGHT.getValue(), CardColorEnum.SPADES.getValue()),
                new Card(CardValueEnum.AS.getValue(), CardColorEnum.HEARTS.getValue()), new Card(CardValueEnum.QUEEN.getValue(), CardColorEnum.CLUBS.getValue()),
                new Card(CardValueEnum.TEN.getValue(), CardColorEnum.DIAMONDS.getValue()));

        cardService.sortCards(cards, getPokerValueOrder(), getColorOrder());

        assertEquals(CardValueEnum.EIGHT.getValue(), cards.get(0).getValue());
        assertEquals(CardColorEnum.SPADES.getValue(), cards.get(0).getColor());

        assertEquals(CardValueEnum.TEN.getValue(), cards.get(1).getValue());
        assertEquals(CardColorEnum.DIAMONDS.getValue(), cards.get(1).getColor());

        assertEquals(CardValueEnum.QUEEN.getValue(), cards.get(2).getValue());
        assertEquals(CardColorEnum.CLUBS.getValue(), cards.get(2).getColor());

        assertEquals(CardValueEnum.AS.getValue(), cards.get(3).getValue());
        assertEquals(CardColorEnum.HEARTS.getValue(), cards.get(3).getColor());
    }

    @Test
    void sortCard_different_color() {
        List<Card> cards = Arrays.asList(new Card(CardValueEnum.EIGHT.getValue(), CardColorEnum.SPADES.getValue()),
                new Card(CardValueEnum.EIGHT.getValue(), CardColorEnum.HEARTS.getValue()), new Card(CardValueEnum.EIGHT.getValue(), CardColorEnum.CLUBS.getValue()),
                new Card(CardValueEnum.EIGHT.getValue(), CardColorEnum.DIAMONDS.getValue()));

        cardService.sortCards(cards, getPokerValueOrder(), getColorOrder());

        assertEquals(CardValueEnum.EIGHT.getValue(), cards.get(0).getValue());
        assertEquals(CardColorEnum.DIAMONDS.getValue(), cards.get(0).getColor());

        assertEquals(CardValueEnum.EIGHT.getValue(), cards.get(1).getValue());
        assertEquals(CardColorEnum.HEARTS.getValue(), cards.get(1).getColor());

        assertEquals(CardValueEnum.EIGHT.getValue(), cards.get(2).getValue());
        assertEquals(CardColorEnum.CLUBS.getValue(), cards.get(2).getColor());

        assertEquals(CardValueEnum.EIGHT.getValue(), cards.get(3).getValue());
        assertEquals(CardColorEnum.SPADES.getValue(), cards.get(3).getColor());
    }

    @Test
    void sortCard_containing_same_value() {
        List<Card> cards = Arrays.asList(new Card(CardValueEnum.EIGHT.getValue(), CardColorEnum.SPADES.getValue()),
                new Card(CardValueEnum.AS.getValue(), CardColorEnum.HEARTS.getValue()), new Card(CardValueEnum.TWO.getValue(), CardColorEnum.CLUBS.getValue()),
                new Card(CardValueEnum.EIGHT.getValue(), CardColorEnum.DIAMONDS.getValue()), new Card(CardValueEnum.AS.getValue(), CardColorEnum.SPADES.getValue()),
                new Card(CardValueEnum.TWO.getValue(), CardColorEnum.HEARTS.getValue()),new Card(CardValueEnum.AS.getValue(), CardColorEnum.CLUBS.getValue()),
                        new Card(CardValueEnum.KING.getValue(), CardColorEnum.SPADES.getValue()));

        cardService.sortCards(cards, getPokerValueOrder(), getColorOrder());

        assertEquals(8, cards.size());

        assertEquals(CardValueEnum.TWO.getValue(), cards.get(0).getValue());
        assertEquals(CardColorEnum.HEARTS.getValue(), cards.get(0).getColor());

        assertEquals(CardValueEnum.TWO.getValue(), cards.get(1).getValue());
        assertEquals(CardColorEnum.CLUBS.getValue(), cards.get(1).getColor());

        assertEquals(CardValueEnum.EIGHT.getValue(), cards.get(2).getValue());
        assertEquals(CardColorEnum.DIAMONDS.getValue(), cards.get(2).getColor());

        assertEquals(CardValueEnum.EIGHT.getValue(), cards.get(3).getValue());
        assertEquals(CardColorEnum.SPADES.getValue(), cards.get(3).getColor());

        assertEquals(CardValueEnum.KING.getValue(), cards.get(4).getValue());
        assertEquals(CardColorEnum.SPADES.getValue(), cards.get(4).getColor());

        assertEquals(CardValueEnum.AS.getValue(), cards.get(5).getValue());
        assertEquals(CardColorEnum.HEARTS.getValue(), cards.get(5).getColor());

        assertEquals(CardValueEnum.AS.getValue(), cards.get(6).getValue());
        assertEquals(CardColorEnum.CLUBS.getValue(), cards.get(6).getColor());

        assertEquals(CardValueEnum.AS.getValue(), cards.get(7).getValue());
        assertEquals(CardColorEnum.SPADES.getValue(), cards.get(7).getColor());
    }

    private List<String> getPokerValueOrder(){
        return Arrays.asList(CardValueEnum.TWO.getValue(), CardValueEnum.THREE.getValue(), CardValueEnum.FOUR.getValue(), CardValueEnum.FIVE.getValue(),
                CardValueEnum.SIX.getValue(), CardValueEnum.SEVEN.getValue(), CardValueEnum.EIGHT.getValue(), CardValueEnum.NINE.getValue(),
                CardValueEnum.TEN.getValue(), CardValueEnum.JACK.getValue(), CardValueEnum.QUEEN.getValue(), CardValueEnum.KING.getValue(),
                CardValueEnum.AS.getValue());
    }

    private List<String> getColorOrder(){
        return Arrays.asList(CardColorEnum.DIAMONDS.getValue(), CardColorEnum.HEARTS.getValue(), CardColorEnum.CLUBS.getValue(), CardColorEnum.SPADES.getValue());
    }
}