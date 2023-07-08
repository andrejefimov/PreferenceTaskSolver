package pref;

import java.util.HashMap;

public class Card {
    private final Suit suit;
    private final Value value;

    private final static HashMap<Suit, HashMap<Value, Card>> deck = new HashMap<>();

    static {
        for (Suit suit : Suit.values()) {
            deck.put(suit, new HashMap<>());
            for (Value value : Value.values()) {
                deck.get(suit).put(value, new Card(suit, value));
            }
        }
    }

    private Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static Card card(Suit suit, Value value) {
        return deck.get(suit).get(value);
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value) + suit;
    }
}
