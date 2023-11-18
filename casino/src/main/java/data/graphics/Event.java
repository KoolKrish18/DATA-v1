package data.graphics;

import data.main.Board;
import data.main.Case;
import data.main.Main;

public abstract class Event {

    public abstract void onEvent();

    private Event() {

    }
    
    public static Event caseSelection(Case c){

        return new Event() {

            @Override
            public void onEvent() {
                Main.board.chooseCase(c.id);
            }
            
        };

    }

}
