package data.graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class PanelGraphics extends JPanel{

    protected abstract void initVars();
    protected abstract void update();
    public abstract void paint();
    
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paint();
    }

}
