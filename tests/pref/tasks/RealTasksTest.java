package pref.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pref.Hand;
import pref.NormalGameState;
import pref.RoundState;

import java.util.Arrays;
import java.util.List;

import static pref.Card.card;
import static pref.Suit.*;
import static pref.Value.*;
import static pref.Value.Queen;

public class RealTasksTest {
    @Test
    public void task1() {
        Hand firstHand = new Hand(Arrays.asList(
                card(Spades, Ace),
                card(Spades, King),
                card(Spades, Jack),
                card(Spades, Eight),
                card(Clubs, King),
                card(Clubs, Jack),
                card(Clubs, Eight),
                card(Clubs, Seven),
                card(Hearts, King),
                card(Hearts, Queen)));
        Hand secondHand = new Hand(Arrays.asList(
                card(Spades, Queen),
                card(Spades, Ten),
                card(Spades, Seven),
                card(Clubs, Ace),
                card(Clubs, Queen),
                card(Diamonds, King),
                card(Diamonds, Queen),
                card(Diamonds, Nine),
                card(Diamonds, Eight),
                card(Hearts, Nine)));
        Hand thirdHand = new Hand(Arrays.asList(
                card(Spades, Nine),
                card(Clubs, Ten),
                card(Clubs, Nine),
                card(Diamonds, Ace),
                card(Diamonds, Ten),
                card(Diamonds, Seven),
                card(Hearts, King),
                card(Hearts, Jack),
                card(Hearts, Ten),
                card(Hearts, Eight)));

        List<Hand> hands = Arrays.asList(firstHand, secondHand, thirdHand);
        NormalGameState normalGameState = new NormalGameState(hands, new RoundState(), 2);

        Assertions.assertEquals(5, normalGameState.numOfTricksWithTrump(Clubs, 0));
    }
}
