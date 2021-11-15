package com.domgarr.michiganrummy.service;

import com.domgarr.michiganrummy.common.Rank;
import com.domgarr.michiganrummy.common.Suit;
import com.domgarr.michiganrummy.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckService {
    public List<Card> generateDeck() {
        List<Card> cards = Arrays.stream(Suit.values())
                .map(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(suit, rank))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return cards;
    }
}
