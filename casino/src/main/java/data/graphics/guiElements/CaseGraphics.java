package data.graphics.guiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import java.util.HashMap;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Image;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import data.graphics.Event;
import data.graphics.EventManager;
import data.main.Case;

public class CaseGraphics extends JButton {

    public static int width = 175;
    public static int height = 125;

    public boolean opening = false;
    Case c;

    int animationFrame = 0;

    public static ImageIcon icon = new ImageIcon(
            Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\Chest_Closed.png");
    public static ImageIcon openIcon = new ImageIcon(
            Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\Chest_Opened.png");
    public static Image glow;

    public static HashMap<String, ImageIcon> tiles;

    public static void initCaseGraphics() {

        String path = Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\tiles\\";

        tiles = new HashMap<String, ImageIcon>();
        try {
            glow = ImageIO.read(
                    new File(Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\Glow.png"));

            tiles.put("+1", new ImageIcon(path + "Add1.png"));
            tiles.put("+2", new ImageIcon(path + "Add2_2.png"));
            tiles.put("+3", new ImageIcon(path + "Add3.png"));
            tiles.put("-1", new ImageIcon(path + "Minus1.png"));
            tiles.put("-2", new ImageIcon(path + "Minus2.png"));
            tiles.put("-3", new ImageIcon(path + "Minus3.png"));
            tiles.put("x2", new ImageIcon(path + "2x_Multiplier.png"));
            tiles.put("x3", new ImageIcon(path + "3X_Multiplier_1.png"));
            tiles.put("Instadeath", new ImageIcon(path + "Instant_Death.png"));
            tiles.put("Full Cupcake", new ImageIcon(path + "Full_Cupcake.png"));
            tiles.put("Half Cupcake", new ImageIcon(path + "Half-A_Cupcake.png"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Constructor
    public CaseGraphics(Case c) {

        // Sets the case instance variable for ease of access
        this.c = c;

        setSize(new Dimension(width, height)); // Sets size to standard size
        // setBackground (new Color(0, 0, 0, 0)); // Sets background colour (very
        // temporary)
        setOpaque(false);
        setContentAreaFilled(false);
        setVisible(true);
        setBorderPainted(false);
        setFocusPainted(false);

        setIcon(icon);
        setDisabledIcon(icon);

        // Adds an ActionListener that disables this button and queues up the
        // "CaseSelected" event
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.addEvent(Event.caseSelection(c));
                setEnabled(false);
            }

        });

    }

    public void open() {
        opening = true;
        setSize(175, 300);
        setLocation(getX(), getY() - 87 - 88);
        setDisabledIcon(openIcon);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (opening) {
            if (animationFrame < 45) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animationFrame / 45f));
                g2d.drawImage(glow, 0, 88, null);

                animationFrame++;
            } else {

                Image img = tiles.get(c.toString()).getImage();
                Image newImg = img.getScaledInstance(width, height + 50, java.awt.Image.SCALE_SMOOTH);
                ImageIcon n = new ImageIcon(newImg);

                setSize(width, height + 100);
                setLocation(getX(), getY() + 88 + 87 - 50);
                setDisabledIcon(n);
                opening = false;

                EventManager.addEvent(Event.onAnimationEnd(c));
            }
        }

    }

}
