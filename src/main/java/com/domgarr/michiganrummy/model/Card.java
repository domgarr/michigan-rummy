package com.domgarr.michiganrummy.model;

import com.domgarr.michiganrummy.common.Rank;
import com.domgarr.michiganrummy.common.Suit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    private Suit suit;
    private Rank rank;


}
