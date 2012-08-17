package pref;

import java.util.HashMap;

public class Card {
    private Suit suit;
    private Value value;

    private final static HashMap<Suit, HashMap<Value, Card>> deck = new HashMap<Suit, HashMap<Value, Card>>();

    static {
        for (Suit suit : Suit.values()) {
            deck.put(suit, new HashMap<Value, Card>());
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
        StringBuilder builder = new StringBuilder();
        builder.append(value).append(suit);
        return builder.toString();
    }
}
