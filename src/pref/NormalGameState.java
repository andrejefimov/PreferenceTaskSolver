package pref;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class NormalGameState {

    private final static HashMap<NormalGameState, Integer> statesCache = new HashMap<>();

    private final List<Hand> hands;
    private final int activePlayerIndex;
    private final RoundState roundState;

    public NormalGameState(List<Hand> hands, RoundState roundState, int activePlayerIndex) {
        if (hands.size() != 3) {
            throw new IllegalArgumentException("There should be 3 hands");
        }

        if (!(activePlayerIndex >= 0 && activePlayerIndex < 3)) {
            throw new IllegalArgumentException("Invalid index of active player");
        }

        this.roundState = roundState;
        this.hands = hands;
        this.activePlayerIndex = activePlayerIndex;
    }

    // returns the maximum number of tricks won by declarer with a given trump suit
    public int numOfTricksWithTrump(@Nullable Suit trumpSuit, int declarerIndex) {
        if (hands.get(0).getCards().size() == 9) {
            System.out.println(this);
        }

        Hand activePlayer = hands.get(activePlayerIndex);
        int trickResult = activePlayerIndex == declarerIndex ? 0 : Integer.MAX_VALUE;

        for (Card moveCard : getPossibleMoves(activePlayer, roundState, trumpSuit)) {
            Hand newHand = activePlayer.removeCard(moveCard);
            RoundState newRoundState = roundState.addCard(moveCard);

            ArrayList<Hand> newHands = new ArrayList<>(hands);
            newHands.set(activePlayerIndex, newHand);

            NormalGameState newGameState;

            int roundTrick = 0;
            int newActivePlayerIndex;
            if (newRoundState.isFinished()) {
                newActivePlayerIndex = nextPlayerIndex(activePlayerIndex, newRoundState.indexOfWinner(trumpSuit));

                if (newActivePlayerIndex == declarerIndex) {
                    roundTrick = 1;
                }

                if (newHand.isEmpty()) {
                    // Recursion exit
                    return roundTrick;
                }

                newGameState = new NormalGameState(newHands, new RoundState(), newActivePlayerIndex);
            } else {
                newActivePlayerIndex = nextPlayerIndex(activePlayerIndex);
                newGameState = new NormalGameState(newHands, newRoundState, newActivePlayerIndex);
            }

            int stateTricks;

            if (newGameState.roundState.isEmpty()) {
                if (statesCache.containsKey(newGameState)) {
                    stateTricks = statesCache.get(newGameState);
                } else {
                    stateTricks = newGameState.numOfTricksWithTrump(trumpSuit, declarerIndex);
                    statesCache.put(newGameState, stateTricks);
                }
            } else {
                stateTricks = newGameState.numOfTricksWithTrump(trumpSuit, declarerIndex);
            }

            int tricks = stateTricks + roundTrick;
            trickResult = activePlayerIndex == declarerIndex ? Math.max(trickResult, tricks) : Math.min(trickResult, tricks);
        }

        return trickResult;
    }

    public static int nextPlayerIndex(int activePlayerIndex) {
        return nextPlayerIndex(activePlayerIndex, 0);
    }

    public static int nextPlayerIndex(int activePlayerIndex, int winnerShift) {
        return (activePlayerIndex + 1 + winnerShift) % 3;
    }

    public static Collection<Card> getPossibleMoves(Hand hand, RoundState roundState, @Nullable Suit trump) {
        if (!roundState.isEmpty()) {
            Suit moveSuit = roundState.firstMoveSuit();

            Collection<Card> suitCards = hand.getSuitCards(moveSuit);
            if (!suitCards.isEmpty()) {
                return suitCards;
            }

            Collection<Card> trumpCards = hand.getSuitCards(trump);
            if (!trumpCards.isEmpty()) {
                return trumpCards;
            }
        }

        return hand.getCards();
    }

    int hashCodeCache;
    boolean hashCodeReady = false;

    @Override
    public int hashCode() {
        if (!hashCodeReady) {
            hashCodeCache = 17;
            for (Hand hand : hands) {
                hashCodeCache = 31 * hashCodeCache + hand.hashCode();
            }
            hashCodeCache = 31 * hashCodeCache + activePlayerIndex;
            hashCodeCache = 31 * hashCodeCache + roundState.hashCode();

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

        NormalGameState otherState = (NormalGameState) obj;
        if (activePlayerIndex != otherState.activePlayerIndex) {
            return false;
        }

        for (int i = hands.size() - 1; i >= 0; i--) {
            if (!hands.get(i).equals(otherState.hands.get(i))) {
                return false;
            }
        }

        return roundState.equals(otherState.roundState);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("State: ");
        for (Hand hand : hands) {
            builder.append(hand.toString()).append(" ");
        }

        return builder.toString();
    }
}
