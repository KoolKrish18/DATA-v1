package data.main;

import data.graphics.GraphicsManager;
import data.graphics.guiElements.CaseGraphics;

public class Main {

    public static Board board;
    public static GraphicsManager gManager; 
    public static void main(String[] args) {

        CaseGraphics.initCaseGraphics();
        
        gManager = new GraphicsManager();
        
        gManager.startThread();

        play();

    }

    public static void play() {


        gManager.setup();

        board = new Board();

        gManager.addComponent(board.graphics);
        gManager.addComponent(board.chosenCases.graphics);

    }
}
