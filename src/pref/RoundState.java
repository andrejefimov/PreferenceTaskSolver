package pref;

import java.util.ArrayList;
import java.util.List;

public class RoundState {
    private final List<Card> cards;
    private Suit firstMoveSuit;

    public RoundState() {
        cards= new ArrayList<>(3);
    }

    private RoundState(List<Card> cards) {
        if (!cards.isEmpty()) {
            firstMoveSuit = cards.get(0).getSuit();
        }
        this.cards = cards;
    }

    public RoundState addCard(Card card) {
        if (isFinished()) {
            throw new IllegalStateException("Round is already finished");
        }

        ArrayList<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new RoundState(newCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Suit firstMoveSuit() {
        return firstMoveSuit;
    }

    public boolean isFinished() {
        return cards.size() == 3;
    }

    public int indexOfWinner(Suit trump) {
        if (!isFinished()) {
            throw new IllegalStateException("Invalid state of round for getting winner");
        }

        int winnerIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (compare(cards.get(winnerIndex), cards.get(i), trump) < 0) {
                winnerIndex = i;
            }
        }

        return winnerIndex;
    }


    public static int compare(Card first, Card second, Suit trumpSuit) {
        if (first.getSuit() == second.getSuit()) {
            return first.getValue().compareTo(second.getValue());
        } else if (second.getSuit() == trumpSuit) {
            return -1;
        }

        return 1;
    }

    int hashCodeCache;
    boolean hashCodeReady = false;

    @Override
    public int hashCode() {
        if (!hashCodeReady) {
            hashCodeCache = 17;
            for (Card card : cards) {
                hashCodeCache = 31 * hashCodeCache + card.hashCode();
            }

            hashCodeReady = true;
        }
        return hashCodeCache;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        RoundState otherTest = (RoundState) obj;
        if (cards.size() != otherTest.cards.size()) {
            return false;
        }

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != otherTest.cards.get(i)) {
                return false;
            }
        }

        return true;
    }
}
