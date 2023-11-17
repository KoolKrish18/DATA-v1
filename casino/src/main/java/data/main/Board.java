package data.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Board extends ArrayList<ArrayList<Case>> {
    
    public Board() {

        initVars(); // Initializes all of the amounts (say we want two "2x" cases, this allows for quick and easy changes of that)
        
    }

    /*
     * Key:
     * i : iterator
     * m : multiplier
     * numbers : values to be plugged into one of the above
     * n : specifier that the number is negative
     * c : cupcake
     * h : specifier that the cupcake is half
     * d : instadeath
     * 
     * Value:
     * Quantity
     * -- Should sum to 25
     */

    public void initVars() {

        HashMap<String, Integer> temp = new HashMap<String, Integer>(); // HashMap to allow an easy way to randomize the order of the cases

        Random ranGen = new Random(); // Random generator

        ArrayList<String> keys; // Temp variable for use later on
        String chosenKey; // Temp variable for use later on 
        
        // Specific quantities of each Case
        temp.put("i1n", 3); // -1
        temp.put("i2n", 2); // -2
        temp.put("i3n", 1); // -3
        temp.put("i3", 1); // +3
        temp.put("i2", 2); // +2
        temp.put("i1", 3); // +1
        temp.put("m2", 3); // x2
        temp.put("m3", 1); // x3
        temp.put("c", 1); // Full Cupcake
        temp.put("ch", 3); // Half Cupcake
        temp.put("d", 5); // Instadeath

        // Prints the number total quantity of cases for debugging
        System.out.println(temp.values().stream().mapToInt(Integer::valueOf).sum());
        
        for (int row = 0; row < 5; row++) {
            add(new ArrayList<Case>()); // Initializes the new row
            for (int col = 0; col < 5; col++) {

                keys = new ArrayList<String>(temp.keySet()); // Gets a list of all of the keys
                chosenKey = keys.get(ranGen.nextInt(keys.size())); // Chooses a case using the random generator

                get(row).add(decodeKey(chosenKey)); // Adds the chosen case to the board
                temp.replace(chosenKey, temp.get(chosenKey)-1); // Reduces the Quantity of this case in the HashMap by one

                // Checks to see if the Quantity of this case in the HashMap is 0, and if it is, removes it from the HashMap
                if (temp.get(chosenKey) == 0) { 
                    temp.remove(chosenKey);
                }
                
            }
        }
        
    }

    // Decodes the key and returns the corresponding case
    private static Case decodeKey(String key) {

        int value = 1; // Initially sets the value of the tile to 1 to be changed later

        // Checks to see if the case is a Cupcake Case
        if (key.startsWith("c")) {
            // Returns the case created with the parameters of if the key states that it should be half
            return Case.createCupcake(!key.contains("h"));
        } 

        // Checks to see if the case is an Instadeath case
        if (key.startsWith("d")) {
            // Returns the instadeath case
            return Case.createDeath();
        }

        // If there is an "n" in the key, it will be the only case where there are 3 characters and thereby means the value must be negative
        if (key.length() == 3) {
            value = -1;
        }

        // Multiples the value by the number in the key
        value *= Character.getNumericValue(key.charAt(1));
        
        if (key.startsWith("m")) { // Checks to see if the Key specifies it as a Multiplier case, and if it does, returns the case
            return Case.createMultiplier(value);
        } else { 
            // Since there are no remaining cases, it means that the case must be an iterator
            return Case.createIterator(value);
        }

    }

    // Standard built in toString method customized to allow for easy debugging
    public String toString() {

        // Gets all of the tiles from the board and puts them all into one properly formatted "temp" String
        String temp = "";

        // Fills up the String
        for (int row = 0; row < size(); row++) {
            for (int col= 0; col < get(row).size(); col++) {
                temp += String.format("%-15s", getCaseAt(row, col));
            }
            temp += "\n";
        }

        return temp;
    }

    // Returns a case and removes it from the matrix
    public Case chooseCase(int row, int col) {
        
        Case temp = getCaseAt(row, col);
        get(row).remove(col);
        return temp;

    }

    // Method to return the case at a specific row and colomn
    public Case getCaseAt(int row, int col) {
        return get(row).get(col);
    }

}
