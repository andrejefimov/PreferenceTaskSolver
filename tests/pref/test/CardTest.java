package pref.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pref.Suit;
import pref.Value;

import static pref.Card.card;

public class CardTest {
    @Test
    public void toStringTest() {
        Assertions.assertEquals("J♦", card(Suit.Diamonds, Value.Jack).toString());
    }
}
