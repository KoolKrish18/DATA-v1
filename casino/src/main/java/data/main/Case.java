package data.main;

import data.graphics.guiElements.CaseGraphics;

// Case for game
public abstract class Case {

    public abstract int applyChanges(int currentValue); // Method to apply whatever the value of the case is to the current value

    public CaseGraphics graphics;

    private String label; // Variable to be used to display a value on the graphics
    public int id;
    public String caseType;

    public String toString() {
        return label;
    }

    // Raw Constructor
    private Case(String label, String caseType) {
        this.label = label; // Sets the label Variable to the specified label from main file
        this.caseType = caseType;
        graphics = new CaseGraphics(this);

        
    } // end Raw Constructor

    // Creates a multiplier case
    // I.E. x2 or x3
    public static Case createMultiplier(int multiplier) {
        return new Case("x" + multiplier, "m") {

            @Override
            public int applyChanges(int currentValue) {
                // Multiplies the Current value by the multiplier
                return currentValue * multiplier;
            }

        };
    }

    // Creates a iterator case
    // I.E. +1, -2, +2 etc.
    public static Case createIterator(int iteration) {
        // Sets the label for the case
        String label;
        // If the iteration is negative, leave it be, otherwise add a "+" in front of the label
        if (iteration < 0) {
            label = String.valueOf(iteration);
        } else {
            label = "+" + String.valueOf(iteration);
        }

        return new Case(label, "i") {

            @Override
            public int applyChanges(int currentValue) {
                // Adds/Removes the value of the case to the currentValue
                return currentValue + iteration;
            }

        };

    }

    /** Creates a cupcake case, will be fixed later on
     * @param full, boolean method to determine whether or not the cupcake is a full cupcake or a half cupcake
     * @return
     */
    public static Case createCupcake(boolean full) {

        // Creates the proper label depending on if it's full or not
        String label = "";
        if (full) {
            label += "Full ";
        } else {
            label += "Half ";
        }

        label += "Cupcake";

        return new Case(label, "c") {

            @Override
            public int applyChanges(int currentValue) {
                // Unadded cupcake stuff, will get to after the gui is in a good state
                throw new UnsupportedOperationException("Unimplemented cupcake handling");
            }

        };
    }

    // Instadeath cases, self explanatory
    public static Case createDeath() {
        return new Case("Instadeath", "d") {

            @Override
            public int applyChanges(int currentValue) {
                // Unadded death stuff, will get to after the gui is in a good state
                throw new UnsupportedOperationException("Unimplemented death handling");
            }

        };
    }


}
