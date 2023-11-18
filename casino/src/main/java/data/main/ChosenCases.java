package data.main;

import java.util.ArrayList;

import data.graphics.Event;
import data.graphics.EventManager;
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

        if (size() == 5) {
            EventManager.addEvent(Event.endGame(this));
        }
    } 

}
