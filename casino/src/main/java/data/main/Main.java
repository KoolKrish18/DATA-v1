package data.main;

import data.graphics.GraphicsManager;
import data.graphics.guiElements.InfoPanel;

public class Main {

    public static Board board;
    public static GraphicsManager gManager; 
    public static InfoPanel infoPanel;
    public static void main(String[] args) {
        
        gManager = new GraphicsManager();
        gManager.startThread();

        infoPanel = new InfoPanel();

        play();

    }

    public static void play() {

        infoPanel.setVisible(true);

        gManager.setup();

        board = new Board();

        gManager.addComponent(infoPanel);
        gManager.addComponent(board.graphics);
        gManager.addComponent(board.chosenCases.graphics);

    }
}
