package data.main;

import java.util.ArrayList;

import data.graphics.Event;
import data.graphics.EventManager;
import data.graphics.guiElements.ChosenPanel;

public class ChosenCases extends ArrayList<Case> {

    public ChosenPanel graphics; 
    public int amt = 1;

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

    public void order() {
        int minIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (get(i).caseType.equals("m")) {
                swap(i, minIndex);
                minIndex++;
            }
        }
    }

    private void swap(int index1, int index2) {
        for (Case c:this) {
            System.out.print(c + " ");
        }
        System.out.println();
        Case temp = get(index1);
        set(index1, get(index2));
        set(index2, temp);
        for (Case c:this) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

}
