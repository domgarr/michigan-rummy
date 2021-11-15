package com.domgarr.michiganrummy;

import com.domgarr.michiganrummy.common.Rank;
import com.domgarr.michiganrummy.common.Suit;
import com.domgarr.michiganrummy.model.Card;
import com.domgarr.michiganrummy.service.DeckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.domgarr.michiganrummy.common.Rank.ACE;
import static com.domgarr.michiganrummy.common.Rank.KING;
import static com.domgarr.michiganrummy.common.Suit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckServiceTest {

    public static final int MAX_NUM_OF_RANKS = 13;
    private DeckService deckService;

    @BeforeEach
    void init() {
        deckService = new DeckService();
    }

    @Test
    void generateDeck_whenCalled_generateDeckOfSize52() {
        assertEquals(52, deckService.generateDeck().size());
    }

    @Test
    void generateDeck_whenCalled_deckContainsThirteenSpades() {
        AtomicInteger ai = new AtomicInteger();
        deckService.generateDeck()
                .forEach(card -> {
                    if (card.getSuit().equals(SPADES)) {
                        ai.incrementAndGet();
                    }
                });
        assertEquals(MAX_NUM_OF_RANKS, ai.get());
    }

    @Test
    void generateDeck_whenCalled_deckContainsAceAndKingOfAllSuits() {
        Map<Suit, List<Card>> suitToCardsMap = deckService.generateDeck().stream()
                .collect(Collectors.groupingBy(Card::getSuit));

        assertAceAndKingInSuitList(suitToCardsMap, SPADES);
        assertAceAndKingInSuitList(suitToCardsMap, HEARTS);
        assertAceAndKingInSuitList(suitToCardsMap, CLUBS);
        assertAceAndKingInSuitList(suitToCardsMap, DIAMONDS);
    }

    private void assertAceAndKingInSuitList(Map<Suit, List<Card>> suitToCardsMap, Suit suit) {
        List<Card> cards = suitToCardsMap.get(suit);
        assertTrue(cards.contains(new Card(suit, ACE)));
        assertTrue(cards.contains(new Card(suit, KING)));
    }
}
