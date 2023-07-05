package pref.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pref.RoundState;

import static pref.Card.card;
import static pref.Suit.*;
import static pref.Value.*;

public class RoundStateTest {
    @Test
    public void firstCardSuit() {
        RoundState roundState = new RoundState().addCard(card(Diamonds, Ace)).addCard(card(Clubs, Ace));
        Assertions.assertEquals(Diamonds, roundState.firstMoveSuit());
    }

    @Test
    public void firstTakeWithTrump1() {
        RoundState roundState = new RoundState().addCard(card(Spades, Ace)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven));
        Assertions.assertEquals(2, roundState.indexOfWinner(Clubs));
    }

    @Test
    public void firstTakeWithTrump2() {
        RoundState roundState = new RoundState().addCard(card(Spades, Ace)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven));
        Assertions.assertEquals(0, roundState.indexOfWinner(Hearts));
    }

    @Test
    public void firstTakeWithTrump3() {
        RoundState roundState = new RoundState().addCard(card(Spades, Jack)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven));
        Assertions.assertEquals(1, roundState.indexOfWinner(Hearts));
    }
}
