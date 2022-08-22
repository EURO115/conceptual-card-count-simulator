package com.company.simulator;
import java.util.*;

public class Deck {
    /**
     *  This CLass is responsible for generate 'Card' Objects and holding them. The
     *  general processes used in real life decks will be implemented here also. The main
     *  Methods include generating a deck, shuffling for random instances, adding and removing
     *  a card and drawing a card. Counting methods are also generated within this class.
     */

    public ArrayList<Card> deckOfCards;

    public Deck() {
        deckOfCards = new ArrayList<Card>();
    }

    /**
     *  The shuffle method shuffles each element within the collection to a random position. The method
     *  implements and uses 'random()' method.
     */
    public void shuffle() {
        Collections.shuffle(deckOfCards, new Random());
    }

    /**
     * The deckCreation() function takes every Suit and Value within the enum in 'Card' class and
     * generate and add a new card object within our local araylist.
     */
    public void deckCreation() {
        for(Card.Suit cardSuits : Card.Suit.values()) {
            for(Card.Value cardValue : Card.Value.values()) {
                //Generate and add new Card Object
                this.deckOfCards.add(new Card(cardSuits, cardValue));
            }
        }
    }

    /**
     * Below functions operate as basic Deck features. getCard() used to return the card object,
     * addCards() adds the card object to the players hand, cardRemoval() removes the card from
     * the hand and drawCard() selects a random card from getCard() and gives the object to a
     * player.
     * @param card
     * @return
     */

    public Card getCard(int card) {
        return deckOfCards.get(card);
    }

    public void addCards(Card addCard){
        deckOfCards.add(addCard);
    }

    public void cardRemoval(int card) {
        deckOfCards.remove(card);
    }

    public void drawCard(Deck drawCards) {
        deckOfCards.add(drawCards.getCard(0));
        drawCards.cardRemoval(0);
    }

    public int deckSize() {
        return deckOfCards.size();
    }

    /**
     * This function takes the initial size of the deck and adds/removes cards from the hand and returns them to the
     * deck, completely refreshing hands for new games.
     * @param refresh
     */
    public void refresh(Deck refresh) {
        int deckSizes = this.deckOfCards.size();
        for (int i = 0; i < deckSizes; i++) {
            refresh.addCards(this.getCard(i));
        }
        for (int i = 0; i < deckSizes; i++) {
            this.cardRemoval(0);
        }
    }

    /**
     * Function provides value to the enum. It goes through each Card generated and stored within the
     * arraylist and gives them a value based off the enum assigned to it. Function also disctates the value of
     * an ace based on players hand count.
     * @return output
     */
    public int valueOfCard(){
        int output = 0;
        int aceCount = 0;
        for(Card one : this.deckOfCards) {
            switch(one.getValue()){
                case ACE: output += 1; break;
                case TWO: output += 2; break;
                case THREE: output += 3; break;
                case FOUR: output += 4; break;
                case FIVE: output += 5; break;
                case SIX: output += 6; break;
                case SEVEN: output += 7; break;
                case EIGHT: output += 8; break;
                case NINE: output += 9; break;
                case TEN, JACK, QUEEN, KING: output += 10; break;
            }

            for (int i = 0; i < aceCount; i++) {
                if (output > 10) {
                    output += 1;
                }
                else {
                    output += 11;
                }
            }
        }
        return output;
    }

    /**
     * Below functions operate as the counting methods and used in conjucntor with valueOfCard() to simulate
     * gameplay and determine the drawing of cards. Each case is assigned a certain value based off the
     * count method being used.
     * @return
     */
    public int noCount() {
        float runningCount = 0;
        for (Card noCount : this.deckOfCards) {
            switch (noCount.getValue()) {
                case ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING: runningCount += 0; break;
            }
        }
        return (int) runningCount;
    }

    public int HiLo() {
        float runningCount = 0;
        float trueCount = (runningCount/52)*100;
        for (Card count: this.deckOfCards) {
            switch (count.getValue()) {
                case ACE, TEN, JACK, QUEEN, KING: runningCount -= 1; break;
                case TWO, THREE, FOUR, FIVE, SIX: runningCount += 1; break;
                case SEVEN, EIGHT, NINE: runningCount += 0; break;
            }
        }
        return (int) runningCount;
    }

    public int HiOpt() {
        float runningCount = 0;
        for (Card HiCount : this.deckOfCards) {
            switch(HiCount.getValue()) {
                case ACE, TWO, SEVEN, EIGHT, NINE: runningCount += 0; break;
                case THREE, FOUR, FIVE, SIX: runningCount += 1; break;
                case TEN, JACK, QUEEN, KING: runningCount -= 1; break;
            }
        }
        return (int) runningCount;
    }

    public int HiOpt2() {
        float runningCount = 0;
        for (Card count2 : this.deckOfCards) {
            switch(count2.getValue()) {
                case ACE, EIGHT, NINE: runningCount += 0; break;
                case TWO, THREE, SIX, SEVEN: runningCount += 1; break;
                case FOUR, FIVE: runningCount += 2; break;
                case TEN, JACK, QUEEN, KING: runningCount -= 2; break;
            }
        }
        return (int) runningCount;
    }

    public int KO() {
        float runningCount = 0;
        for (Card KOCount : this.deckOfCards) {
            switch(KOCount.getValue()) {
                case ACE, TEN, JACK, QUEEN, KING: runningCount -= 1; break;
                case TWO, THREE, FOUR, FIVE, SIX, SEVEN: runningCount += 1; break;
                case EIGHT, NINE: runningCount += 0; break;
            }
        }
        return (int) runningCount;
    }

    public int Omega2 () {
        float runningCount = 0;
        for (Card omega : this.deckOfCards) {
            switch(omega.getValue()) {
                case ACE, EIGHT: runningCount += 0; break;
                case TWO, THREE, SEVEN: runningCount += 1; break;
                case FOUR, FIVE, SIX: runningCount += 2; break;
                case NINE: runningCount -= 1; break;
                case TEN, JACK, QUEEN, KING: runningCount -= 2; break;
            }
        }
        return (int) runningCount;
    }

    public int ZenCount() {
        float runningCount = 0;
        for (Card zen : this.deckOfCards) {
            switch (zen.getValue()) {
                case ACE: runningCount -= 1; break;
                case TWO, THREE, SEVEN: runningCount += 1; break;
                case FOUR, FIVE, SIX: runningCount += 2; break;
                case EIGHT, NINE: runningCount += 0; break;
                case TEN, JACK, QUEEN, KING: runningCount -= 2; break;
            }
        }
        return (int) runningCount;
    }

    public int Halves() {
        float runningCount = 0;
        for (Card half : this.deckOfCards) {
            switch(half.getValue()) {
                case ACE, TEN, JACK, QUEEN, KING: runningCount -= 1; break;
                case TWO, SEVEN: runningCount += 0.5; break;
                case THREE, FOUR, SIX: runningCount += 1; break;
                case FIVE: runningCount += 1.5; break;
                case EIGHT: runningCount += 0; break;
                case NINE: runningCount -=0.5; break;
            }
        }
        return (int) runningCount;
    }

    public int KISS() {
        float runningCount = 0;
        for (Card kiss : this.deckOfCards) {
            switch(kiss.getValue()) {
                case ACE, TWO, THREE, SEVEN, EIGHT, NINE: runningCount += 0; break;
                case FOUR, FIVE, SIX: runningCount += 1; break;
                case TEN, JACK, QUEEN, KING: runningCount -= 1; break;
            }
        }
        return (int) runningCount;
    }

    public int Noir() {
        float runningCount = 0;
        for (Card noir : this.deckOfCards) {
            switch(noir.getValue()) {
                case ACE, TEN, JACK, QUEEN, KING: runningCount -= 2; break;
                case TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE: runningCount += 1; break;
            }
        }
        return (int) runningCount;
    }

    public int scaleCount() {
        float runningCount = 0;
        for (Card scale : this.deckOfCards) {
            switch(scale.getValue()) {
                case ACE, SEVEN, EIGHT: runningCount += 0; break;
                case TWO, THREE, FOUR, FIVE: runningCount += 3; break;
                case SIX: runningCount += 2; break;
                case NINE: runningCount -= 2; break;
                case TEN, JACK, QUEEN, KING: runningCount -= 3; break;
            }
        }
        return (int) runningCount;
    }
}