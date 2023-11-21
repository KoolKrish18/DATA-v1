package data.graphics;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GraphicsManager implements Runnable {

    public GraphicsManager() {
        initVars();
    }

    private ArrayList<PanelGraphics> components;

    private JFrame window;
    private Thread thread;
    final int FPS = 60;

    private Boolean fullScreen = true; // Variable to display as fullscreen or windowed (will be fullscreen for the
                                       // casino day)

    public void setup() {

        try {
            reset();
        } catch (NullPointerException ex) {
            // Swalllows exception as this is completely normal
        }

        components = new ArrayList<PanelGraphics>();
    }

    private void reset() {
        for (PanelGraphics pg : components) {
            window.remove(pg);
        }
    }

    public void initVars() {
        window = new JFrame();

        window.setLayout(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Standard close operation so that when you
                                                                        // press the X button on the window it shuts
                                                                        // down the program as well

        if (fullScreen) {
            window.setUndecorated(true); // Removes the banner at the top of the window
            window.setResizable(false); // Makes it so the window can't be resized
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

    // Multithreading stuff, don't worry about it
    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (thread != null) {

            try {
                updateAll();

                repaintAll();
            } catch (NullPointerException ex) {
                continue;
            }
            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
            }

        }
    }

    private void updateAll() {
        EventManager.handleEvents(); // On each frame, handles all of the pending events
        components.forEach(comp -> comp.update()); // Calls the "update" function for all of the components
    }

    private void repaintAll() {
        components.forEach(comp -> comp.repaint()); // Repaints all of the components, may be used later for further
                                                    // detailing
    }

}
