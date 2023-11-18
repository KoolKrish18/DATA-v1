package data.graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import data.main.Main;

public class GraphicsManager implements Runnable {

    public GraphicsManager() {
        initVars();
    }

    private ArrayList<PanelGraphics> components = new ArrayList<PanelGraphics>();
    
    private JFrame window;
    private Thread thread;
    final int FPS = 60;

    private Boolean fullScreen = false;
    
    public void initVars() {
        window = new JFrame();
        
        window.setLayout(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if (fullScreen) {
            window.setUndecorated(true);
            window.setResizable(false);
            window.setSize(new Dimension(1920, 1080));
        } else {
            window.setSize(new Dimension(1920, 1080));
        }

        window.setVisible(true);
        
    }

    public void addComponent(PanelGraphics c) {
        components.add(c);
        window.add(c);
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while (thread != null) {

            updateAll();

            repaintAll();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {}

        }
    }

    private void updateAll() {
        EventManager.handleEvents();
        components.forEach(comp -> comp.update());
    }

    private void repaintAll() {
        components.forEach(comp -> comp.repaint());
    }

}
