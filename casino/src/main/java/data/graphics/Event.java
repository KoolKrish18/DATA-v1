package data.graphics;

import data.main.Board;
import data.main.Case;
import data.main.ChosenCases;
import data.main.Main;

public abstract class Event {

    public abstract void onEvent();

    private Event() {}
    
    // Event that can get created whenever a case is pressed
    public static Event caseSelection(Case c) {

        return new Event() {

            @Override
            public void onEvent() {
                Board board = Main.board;
                board.chooseCase(c.id);
                board.chosenCases.addCase(c);

                c.graphics.setText(c.toString());

                board.graphics.remove(c.graphics);
            }
            
        };

    }

    public static Event endGame(ChosenCases c) {

        return new Event() {

            @Override
            public void onEvent() {
                Board board = Main.board;
                board.graphics.lockButtons();
                board.graphics.setVisible(false);
                board.chosenCases.graphics.isEnding = true;
                // Sort all cases
                // Start end animation
            }
            
        };
    }

}
