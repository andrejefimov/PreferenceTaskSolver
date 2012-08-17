package pref;

public enum Suit {
    Spades(0),
    Clubs(1),
    Diamonds(2),
    Hearts(3);

    private int index;

    Suit(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        switch (this) {
            case Spades:
                return "♠";
            case Clubs:
                return "♣";
            case Diamonds:
                return "♦";
            case Hearts:
                return "♥";
        }

        throw new IllegalStateException("Unknown");
    }
}
