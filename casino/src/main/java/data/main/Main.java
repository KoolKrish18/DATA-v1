package data.main;

import java.util.Scanner;

import data.graphics.GraphicsManager;

public class Main {

    public static Board board;
    public static GraphicsManager gManager; 
    public static void main(String[] args) {
        
        gManager = new GraphicsManager();

        System.out.println("Making Board...");
        board = new Board();
        System.out.println("Board made");

        System.out.println("Printing Board...");
        System.out.println(board);
        System.out.println("Board printed");
        
        Scanner keyedInput = new Scanner(System.in);
        
        final int SQUARE = 4;
        final int CUBE = 16;
        
        int[] cases = new int[CUBE];
        
        String game;
        
        int usertickets = 0;

        gManager.addComponent(board.graphics);
        gManager.addComponent(board.chosenCases.graphics);
        gManager.startThread();
        
        for (int k = 0; k < SQUARE; k++) {
            for (int l = 0; l < SQUARE; l++) {
                cases[k + (l * SQUARE)] = k + (l * SQUARE);
            }
        }

        System.out.println("Enter 'e' to exit or any other key to continue:");

        
        game = keyedInput.next();

        
        while (!game.equalsIgnoreCase("e")) {
            System.out.println("Choose a case (enter a number between 0 and 15):");
            int chosenCaseIndex = keyedInput.nextInt();
            keyedInput.nextLine();
            
            // Check if the chosenCaseIndex is within the valid range
            // Inside the while loop
            if (chosenCaseIndex >= 0 && chosenCaseIndex < CUBE) {
                // Call the chooseCase method from the Board class
                Case chosenCase = board.chooseCase(chosenCaseIndex / SQUARE, chosenCaseIndex % SQUARE);
                
                // Print information about the chosen case
                System.out.println("Chosen case index: " + chosenCaseIndex);
                System.out.println("Chosen case: " + chosenCase);
                usertickets++;
                
                // Check if the user has chosen 5 cases
                if (usertickets == 5) {
                    System.out.println("You've chosen 5 cases. Exiting the game.");
                    break;
                }
            } else {
                System.out.println("Invalid case index. Please choose a number between 0 and 15.");
            }
            
            System.out.println("Enter 'e' to exit or any other key to continue:");
            game = keyedInput.next();
        }
        
        keyedInput.close();
    }
}
