package data.graphics.guiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import data.graphics.PanelGraphics;

public class InfoPanel extends PanelGraphics {

    public InfoPanel() {
        initVars();
    }

    @Override
    protected void initVars() {
        setLocation(1920*3/4, 0);
        setSize(new Dimension(1920*1/4, 1080 * 5/6)); // Sets the size to 75% of the width and 67% of the height
        setBackground(Color.BLUE); // Sets the background colour (Very temorary)

        setLayout(null); // Sets the layout to null to allow for absolute element placement
    }

    @Override
    protected void update() {

    }

    @Override
    public void draw(Graphics g) {

    }
    
}
