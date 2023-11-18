package data.main;

import java.util.ArrayList;

import data.graphics.guiElements.ChosenPanel;

public class ChosenCases extends ArrayList<Case> {

    public ChosenPanel graphics; 

    public ChosenCases() {
        graphics = new ChosenPanel(this);
    }

    public void addCase(Case c) {
        add(c);
        System.out.println("added " + c);
        graphics.add(c.graphics);
    } 

}
