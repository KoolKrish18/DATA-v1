package data.graphics.guiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import data.graphics.Event;
import data.graphics.EventManager;
import data.graphics.PanelGraphics;
import data.main.ChosenCases;

public class ChosenPanel extends PanelGraphics {

    public ChosenCases chosenCases;
    int t = 0; // Time var for end animation
    public Boolean isEnding = false; // For animation
    public Boolean caseOpening = false;

    public JButton resetButton;
    public JLabel moneyAmt;

    private int openIndex = 0;

    public ChosenPanel(ChosenCases chosenCases) {
        this.chosenCases = chosenCases; // Access to the chosen cases class
        initVars(); 
    }

    @Override
    protected void initVars() {

        setLayout(null); // Sets the layout to null for Absoute placing
        setBackground(Color.CYAN); // Sets the background colour (very temporary)
    
        setLocation(new Point(0, 1080*5/6)); // Sets the location to the left wall and 67% of the height down
        setSize(new Dimension(1920, 1080 * 1/6)); // Sets the Size to 75% of the width and 33% of the height
        setVisible(true);

        initResetButton();
        initMoneyAmt();

        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if (caseOpening) {
                    EventManager.addEvent(Event.openCase(ChosenPanel.this));
                }
            }
        });

    }

    public void initMoneyAmt() {
        moneyAmt = new JLabel("Current Money: $1");

        moneyAmt.setSize(getWidth(), 72);
        moneyAmt.setLocation(0, 125);
        moneyAmt.setHorizontalAlignment(JLabel.CENTER);
        moneyAmt.setVisible(false);
        moneyAmt.setFont(getFont().deriveFont(72f));
        moneyAmt.setForeground(Color.WHITE);

        add(moneyAmt);
    }

    public void initResetButton() {
        resetButton = new JButton();

        resetButton.setSize(50, 50);
        resetButton.setLocation(getWidth() - 35 - resetButton.getWidth(), 25);
        resetButton.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\reset.png"));
        resetButton.setBackground(new Color(0, 0, 0, 0));
        resetButton.setBorderPainted(false);
        resetButton.setVisible(true);

        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.addEvent(Event.reset());
            }
            
        });

        add(resetButton);
    }

    @Override
    protected void update() {
        if (isEnding) {
            animateEnd();
            alignCases();
        } else if (!caseOpening) {
            alignCases();
        }

        moneyAmt.setText("Current Money: $" + chosenCases.amt);

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
    public void draw(Graphics g) {
        try {
            Image img = ImageIO.read(new File(Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\Background2.png"));
            g.drawImage(img, 0, 0, getWidth(), getHeight(),null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void animateEnd() {
        t++;
        if (getY() <= 0) {
            setSize(WIDTH, 1080);
            setLocation(0, 0);
        } else {
            
            int y;

            y = 5*1080/6 - t*18;

            setLocation(0, y);
        }

        if (getHeight() < 1080) {
            int height;
            height = t*18 + 1080/6;
            setSize(1920, height);
        } else {
            setSize(1920, 1080);
        }

        if (getHeight() <= 1080 && getY() <= 0) {
            isEnding = false;
            moneyAmt.setVisible(true);
        }
    }

    public void openChest() {
        if (openIndex < chosenCases.size()) {
            chosenCases.get(openIndex).graphics.open();
            openIndex++;
        }
    }
    
}
