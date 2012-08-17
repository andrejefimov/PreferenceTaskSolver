package pref.test;

import junit.framework.Assert;
import org.junit.Test;
import pref.Suit;
import pref.Value;

import static pref.Card.card;

public class CardTest {
    @Test
    public void toStringTest() {
        Assert.assertEquals("J♦", card(Suit.Diamonds, Value.Jack).toString());
    }
}
