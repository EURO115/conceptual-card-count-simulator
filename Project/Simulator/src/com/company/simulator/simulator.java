package com.company.simulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class simulator {

    public static void main(String[] args) throws IOException {

        int run;
        int repeat;

        /**
         * Block of code used to set-up the simulation, using user input.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Blackjack Simulator!");
        System.out.println("Please input the amount of games you wish to simulate?: ");
        run = input.nextInt();
        System.out.println("Please input the amount of times you wish for said runs to be repeated?: ");
        repeat = input.nextInt();
        System.out.println("Please Input The counting method you'll be using?: ");
        String count = input.next();
        System.out.println("Performing Simulations using the " + count + " counting method!");

        /**
         * If condtion placed to lop through the Blackjack class and output the toal wins, draws and
         * losses each set of games had. Results are printed out in percentile.
         */
        for (int i = 0; i < repeat; i++) {
            System.out.println("Simulation run: " + (i+1));
            Blackjack bj = new Blackjack(run);
            System.out.println("Wins: " + bj.wins + ", Losses: " + bj.losses + ", draws: " + bj.draws);
            Object winPercent = (bj.wins/bj.simEnd)*100;
            Object lossPercent = (bj.losses/bj.simEnd)*100;
            Object drawPercent = (bj.draws/bj.simEnd)*100;
            System.out.println("Win Percentage: " + winPercent + "%" + ", Loss Percentage: " +
                    lossPercent + "%" + ", Draw Percentage: " + drawPercent + "%");
            System.out.println("----------------------------------------------------------------------------------");

            /**
             * File style method implemented which records each set of games played and outputs the results into
             * the select pathname below. Will keep a record of all past games played.
             */
            File out = new File("output.txt");
            FileWriter fileWriter = new FileWriter(out, true);
            BufferedWriter buff = new BufferedWriter(fileWriter);
            if (i == 0) {
                buff.write("Games Simulated: " + run + ", Times repeated: " + repeat + ", Count Method used: " + count + '\n');
            }
            buff.write("Details: " + " Wins: " + winPercent + "%" + ", Losses: " + lossPercent + "%" + ", Draws: " + drawPercent + "%" + '\n');
            if (i == (repeat-1)) {
                buff.write("Simulation Batch complete. Records of this simulation will be on file permanently until deleted/refreshed" + '\n');
                buff.write("====================================================================================" + '\n');
            }
            buff.close();
        }

        System.out.println("---Simulations have Ended!---");
    }
}
