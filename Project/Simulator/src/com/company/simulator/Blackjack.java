package com.company.simulator;

public class Blackjack {

    public float wins, draws, losses;
    public int simEnd = 0;

    public Blackjack(int run) {
        System.out.println("Beginning Simulation!");

        Deck pD = new Deck();
        pD.deckCreation();
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        pD.shuffle();

        /**
         * The first while loop used to repeat everything within the class. The loop will keep repeating based
         * on the value within the class param.
         */
        while (run > 0) {

            //Generate players Hand
            playerDeck.drawCard(pD);
            playerDeck.drawCard(pD);

            //Generate Dealer Hand
            dealerDeck.drawCard(pD);
            dealerDeck.drawCard(pD);

            //Boolean value which is used to simulate if the end of a round has been reached.
            boolean EOR = false;

            /**
             * An inner while loop nested that checks the state of the players hand and take action based off
             * the condition. This is the only loop that involves all the players actions.
             */
            while (true) {
                //Change the counting method based on which one you wish to use
                if (playerDeck.valueOfCard() < 21 && playerDeck.Omega2() >= 0) {
                    playerDeck.drawCard(pD);
                    if (playerDeck.valueOfCard() > 21) {
                        losses++;
                        EOR = true;
                        break;
                    }
                } else if (playerDeck.valueOfCard() > dealerDeck.valueOfCard() && playerDeck.valueOfCard() < 21){
                    wins++;
                    EOR = true;
                    break;
                } else {
                    break;
                }
            }

            /**
             * further Set statements below are more player actions based on their current hand compared to the dealers.
             */
            //Change the counting method based on which one you wish to use
            if (playerDeck.valueOfCard() < dealerDeck.valueOfCard() && playerDeck.Omega2() >= 0) {
                playerDeck.drawCard(pD);
                if (playerDeck.valueOfCard() > dealerDeck.valueOfCard() && playerDeck.valueOfCard() < 21 && !EOR) {
                    wins++;
                } else {
                    losses++;
                }
                EOR = true;
            }

            //Change the counting method based on which one you wish to use
            if (playerDeck.valueOfCard() < dealerDeck.valueOfCard() && playerDeck.Omega2() >=0 && !EOR) {
                losses++;
                EOR = true;
            }

            //Change the counting method based on which one you wish to use
            if (playerDeck.valueOfCard() > dealerDeck.valueOfCard() && playerDeck.Omega2() >= 0 && !EOR) {
                wins++;
                EOR = true;
            }

            //Change the counting method based on which one you wish to use
            if (playerDeck.valueOfCard() < dealerDeck.valueOfCard() && playerDeck.Omega2() >= 0 && playerDeck.valueOfCard() < 21) {
                playerDeck.drawCard(pD);
                if (playerDeck.valueOfCard() > dealerDeck.valueOfCard() && playerDeck.valueOfCard() < 21 && !EOR) {
                    wins++;
                    EOR = true;
                }
            }

            /**
             * If statement and nested while loop involve the dealers side of Blackjack and focus on ensuring
             * the dealer ha sa minimum hand count of 17.
             */
            if (dealerDeck.valueOfCard() > playerDeck.valueOfCard() && dealerDeck.valueOfCard() >= 17 && !EOR) {
                losses++;
                EOR = true;
            }
            while (dealerDeck.valueOfCard() < 17 && !EOR) {
                dealerDeck.drawCard(pD);
                if (dealerDeck.valueOfCard() > 21 && !EOR) {
                    wins++;
                    EOR = true;
                }
            }

            /**
             * Default condition states that do not involve card counting and merely look at the card
             * value and determine if the player ha swon, drawn or lost.
             */
            if (playerDeck.valueOfCard() == dealerDeck.valueOfCard() && !EOR) {
                draws++;
                EOR = true;
            }

            if (playerDeck.valueOfCard() > dealerDeck.valueOfCard() && !EOR) {
                wins++;
                EOR = true;
            }
            else if (!EOR) {
                losses++;
                EOR = true;
            }

            /**
             * Refresh the player and dealers hand after a successful game
             */
            playerDeck.refresh(pD);
            dealerDeck.refresh(pD);

            simEnd++;
            if (run == simEnd) {
                System.out.println("End");
                break;
            }
        }
    }
}