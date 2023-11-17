package data.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyedInput = new Scanner(System.in);

        final int SQUARE = 4;
        final int CUBE = 16;

        int[] cases = new int[CUBE];

        String game;

        int usertickets;

        for (int k = 0; k < SQUARE; k++) {
            for (int l = 0; l < SQUARE; l++) {
                cases[k + (l * SQUARE)] = k + (l * SQUARE);
            }
        }
        game = keyedInput.next();
        while ((game.equalsIgnoreCase("e")) == false) {

            usertickets = 0;

        }
    }
}
