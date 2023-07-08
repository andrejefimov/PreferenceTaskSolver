package pref.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pref.Hand;

import java.util.Arrays;

import static pref.Card.card;
import static pref.Suit.Hearts;
import static pref.Suit.Spades;
import static pref.Value.*;

public class HandTest {
    @Test
    public void equalsTestTrue() {
        Assertions.assertEquals(
                new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, Ace))),
                new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, Ace))));
    }

    @Test
    public void equalsTest() {
        Hand hand1 = new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, King)));
        Hand hand2 = new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, Ace)));
        Assertions.assertNotEquals(hand1, hand2);
    }
}
