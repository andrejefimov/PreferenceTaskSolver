package pref.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pref.Card;
import pref.Hand;
import pref.NormalGameState;
import pref.RoundState;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static pref.Suit.*;
import static pref.Value.*;
import static pref.Card.card;

public class NormalGameStateTest {
    private Hand hand;

    @BeforeEach
    public void setUp() {
        hand = new Hand(Arrays.asList(
                card(Spades, Ace),
                card(Spades, King),
                card(Spades, Seven),
                card(Clubs, King),
                card(Clubs, Queen)
        ));
    }

    @Test
    public void testGuaranteedTricks() {
        Hand firstHand = new Hand(Arrays.asList(
                card(Spades, Ace),
                card(Clubs, Ace),
                card(Diamonds, Ace)));
        Hand secondHand = new Hand(Arrays.asList(
                card(Spades, King),
                card(Clubs, King),
                card(Diamonds, King)));
        Hand thirdHand = new Hand(Arrays.asList(
                card(Spades, Queen),
                card(Clubs, Queen),
                card(Diamonds, Queen)));

        List<Hand> hands = Arrays.asList(firstHand, secondHand, thirdHand);
        int tricks = new NormalGameState(hands, new RoundState(), 0).numOfTricksWithTrump(null, 0);
        Assertions.assertEquals(3, tricks);
    }

    @Test
    public void testNumberTricks() {
        Hand firstHand = new Hand(Arrays.asList(
                card(Spades, Seven),
                card(Hearts, Ace),
                card(Hearts, King)));
        Hand secondHand = new Hand(Arrays.asList(
                card(Spades, King),
                card(Clubs, King),
                card(Diamonds, King)));
        Hand thirdHand = new Hand(Arrays.asList(
                card(Spades, Queen),
                card(Clubs, Queen),
                card(Diamonds, Queen)));

        List<Hand> hands = Arrays.asList(firstHand, secondHand, thirdHand);
        Assertions.assertEquals(2, new NormalGameState(hands, new RoundState(), 0).numOfTricksWithTrump(null, 0)); // First one goes first
        Assertions.assertEquals(0, new NormalGameState(hands, new RoundState(), 2).numOfTricksWithTrump(null, 0)); //
    }

    @Test
    public void testPossibleMovesNoSuitNoTrump() {
        RoundState roundState = new RoundState();
        roundState.addCard(card(Diamonds, Queen));
        roundState.addCard(card(Clubs, Seven));

        Collection<Card> possibleMoves = NormalGameState.getPossibleMoves(hand, roundState, Hearts);

        Card[] expectedCards = hand.getCards().toArray(new Card[hand.getCards().size()]);
        Card[] actualCards = possibleMoves.toArray(new Card[possibleMoves.size()]);

        Assertions.assertArrayEquals(expectedCards, actualCards);
    }

    @Test
    public void testPossibleMovesWithTrump() {
        RoundState roundState = new RoundState().addCard(card(Diamonds, Queen)).addCard(card(Clubs, Seven));

        Collection<Card> possibleMoves = NormalGameState.getPossibleMoves(hand, roundState, Clubs);

        Card[] expectedCards = new Card[]{card(Clubs, King), card(Clubs, Queen)};
        Card[] actualCards = possibleMoves.toArray(new Card[possibleMoves.size()]);

        Assertions.assertArrayEquals(expectedCards, actualCards);
    }

    @Test
    public void testPossibleMovesWithSameSuit() {
        RoundState roundState = new RoundState().addCard(card(Spades, Queen)).addCard(card(Clubs, Seven));

        Collection<Card> possibleMoves = NormalGameState.getPossibleMoves(hand, roundState, Clubs);

        Card[] expectedCards = new Card[]{card(Spades, Ace), card(Spades, King), card(Spades, Seven)};
        Card[] actualCards = possibleMoves.toArray(new Card[possibleMoves.size()]);

        Assertions.assertArrayEquals(expectedCards, actualCards);
    }

    @Test
    public void testPossibleMovesWithEmptyRound() {
        Collection<Card> possibleMoves = NormalGameState.getPossibleMoves(hand, new RoundState(), Spades);

        Card[] expectedCards = hand.getCards().toArray(new Card[hand.getCards().size()]);
        Card[] actualCards = possibleMoves.toArray(new Card[possibleMoves.size()]);

        Assertions.assertArrayEquals(expectedCards, actualCards);
    }
}
