package pref;

public enum Value {
    Seven(0),
    Eight(1),
    Nine(2),
    Ten(3),
    Jack(4),
    Queen(5),
    King(6),
    Ace(7);

    private int index;

    Value(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        switch (this) {
            case Seven:
                return "7";
            case Eight:
                return "8";
            case Nine:
                return "9";
            case Ten:
                return "10";
            case Jack:
                return "J";
            case Queen:
                return "Q";
            case King:
                return "K";
            case Ace:
                return "A";
        }

        throw new IllegalStateException("Unknown");
    }
}
