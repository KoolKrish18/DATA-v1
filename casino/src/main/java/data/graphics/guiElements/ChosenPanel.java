package data.graphics.guiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import data.graphics.PanelGraphics;
import data.main.ChosenCases;

public class ChosenPanel extends PanelGraphics {

    public ChosenCases chosenCases;
    int t = 0; // Time var for end animation
    public Boolean isEnding = false;

    public ChosenPanel(ChosenCases chosenCases) {
        this.chosenCases = chosenCases; // Access to the chosen cases class
        initVars(); 
    }

    @Override
    protected void initVars() {

        setLayout(null); // Sets the layout to null for Absoute placing
        setBackground(Color.CYAN); // Sets the background colour (very temporary)
    
        setLocation(new Point(0, 1080*2/3)); // Sets the location to the left wall and 67% of the height down
        setSize(new Dimension(1920 * 3/4, 1080 * 1/3)); // Sets the Size to 75% of the width and 33% of the height
        setVisible(true);

    }

    @Override
    protected void update() {
        alignCases();
        if (isEnding) {
            animateEnd();
        }
    }

    // Aligns cases
    public void alignCases() {

        int minX = 50;

        int nWidth = getWidth() - minX*2;
        int gap = nWidth - CaseGraphics.width*5;
        
        gap /= 6;

        int x;
        int y = getHeight() - CaseGraphics.height;
        y /= 2;

        for (int i = 0; i < chosenCases.size(); i++) {
            x = minX;
            x += gap * (i+1);
            x += CaseGraphics.width * i; 

            chosenCases.get(i).graphics.setLocation(x, y);
        }

    }

    @Override
    public void paint() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'paint'");
    }

    public void animateEnd() {
        t++;
        if (getY() <= 0) {
            setSize(WIDTH, 1080);
            setLocation(0, 0);
        } else {
            
            int y;

            y = 2*1080/3 - t*9;

            setLocation(0, y);
        }

        if (getHeight() < 1080) {
            int height;
            height = t*9 + 1080/3;
            setSize(1920, height);
        } else {
            setSize(1920, 1080);
        }

        if (getHeight() < 1080 && getY() <= 0) {
            isEnding = false;
        }
    }
    
}
