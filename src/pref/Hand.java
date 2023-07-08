package pref;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Hand {
    private final List<Card> cards;
    private final List<List<Card>> suitCards;

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.suitCards = new ArrayList<>();

        for (int i = 0; i < Suit.values().length; i++) {
            this.suitCards.add(new ArrayList<>());
        }

        for (Card card : cards) {
            suitCards.get(card.getSuit().getIndex()).add(card);
        }
    }

    private Hand(List<Card> cards, ArrayList<List<Card>> suitCards) {
        this.cards = cards;
        this.suitCards = suitCards;
    }

    public Hand removeCard(Card card) {
        ArrayList<Card> newCards = new ArrayList<>(cards);
        if (!newCards.remove(card)) {
            throw new IllegalAccessError("Hand doesn't contain card");
        }

        ArrayList<List<Card>> newSuitCards = new ArrayList<>(suitCards);
        List<Card> cardCollection = new ArrayList<>(getSuitCards(card.getSuit()));
        if (!cardCollection.remove(card)) {
            throw new IllegalAccessError("Suit cards doesn't contain card");
        }

        newSuitCards.set(card.getSuit().getIndex(), cardCollection);

        return new Hand(newCards, newSuitCards);
    }

    public Collection<Card> getCards() {
        return cards;
    }

    public Collection<Card> getSuitCards(@Nullable Suit suit) {
        if (suit == null) {
            return new ArrayList<>();
        }

        return suitCards.get(suit.getIndex());
    }

    public boolean isEmpty() {
        return cards.isEmpty();
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

        Hand otherHand = (Hand) obj;
        if (otherHand.cards.size() != cards.size()) {
            return true;
        }

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != otherHand.cards.get(i)) {
                return false;
            }
        }

        return true;
    }

    public String toStringCache = null;

    @Override
    public String toString() {
        if (toStringCache == null) {
            StringBuilder builder = new StringBuilder("Hand: ");
            for (Card card : cards) {
                builder.append(card).append(" ");
            }

            toStringCache = builder.toString();
        }

        return toStringCache;
    }
}
