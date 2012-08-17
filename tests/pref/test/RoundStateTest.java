package pref.test;

import junit.framework.Assert;
import org.junit.Test;
import pref.RoundState;

import static pref.Card.card;
import static pref.Suit.*;
import static pref.Value.*;

public class RoundStateTest {
    @Test
    public void firstCardSuit() {
        RoundState roundState = new RoundState().addCard(card(Diamonds, Ace)).addCard(card(Clubs, Ace));
        Assert.assertEquals(Diamonds, roundState.firstMoveSuit());
    }

    @Test
    public void firstTakeWithTrump1() {
        RoundState roundState = new RoundState().addCard(card(Spades, Ace)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven));
        Assert.assertEquals(2, roundState.indexOfWinner(Clubs));
    }

    @Test
    public void firstTakeWithTrump2() {
        RoundState roundState = new RoundState().addCard(card(Spades, Ace)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven));
        Assert.assertEquals(0, roundState.indexOfWinner(Hearts));
    }

    @Test
    public void firstTakeWithTrump3() {
        RoundState roundState = new RoundState().addCard(card(Spades, Jack)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven));
        Assert.assertEquals(1, roundState.indexOfWinner(Hearts));
    }
}
