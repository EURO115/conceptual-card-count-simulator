package com.company.simulator;

public class Card {

    /**
     * Creating two types of variables: Suit class which will store the enums of all card suits and Value class
     * which will store the enums of all card values.
     */
    private final Suit suit;
    public final Value value;

    /**
     * Create two public enums within Card to be accessible and used throughout the program
     */
    public enum Suit {
        CLUBS, SPADES, DIAMONDS, HEARTS
    }

    public enum Value {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
        SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10);
        Value(int i) {
        }
    }

    /**
     * Constructor which produces the layout of a Card object, with the suit being displayed first and then
     * the card value.
     * @param suit
     * @param value
     */
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Value getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Cards: " + this.value + " of " + this.suit + '\n';
    }
}
