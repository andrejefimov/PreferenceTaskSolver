package pref.test;

import junit.framework.Assert;
import org.junit.Test;
import pref.Hand;

import java.util.Arrays;

import static pref.Card.card;
import static pref.Suit.Hearts;
import static pref.Suit.Spades;
import static pref.Value.*;

public class HandTest {
    @Test
    public void equalsTestTrue() {
        Assert.assertEquals(
                new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, Ace))),
                new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, Ace))));
    }

    @Test
    public void equalsTest() {
        Hand hand1 = new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, King)));
        Hand hand2 = new Hand(Arrays.asList(card(Spades, Seven), card(Hearts, Ace)));
        Assert.assertTrue(!hand1.equals(hand2));
    }
}
