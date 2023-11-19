package data.graphics.guiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import data.graphics.Event;
import data.graphics.EventManager;
import data.main.Case;

public class CaseGraphics extends JButton {
    
    public static int width = 175;
    public static int height = 125;
    Case c;

    public static ImageIcon icon = new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\Chest_Closed.png"); 
    public static ImageIcon selectedIcon = new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\casino\\src\\main\\resources\\Chest_Closed_Selected.png"); 
    
    // Constructor
    public CaseGraphics(Case c) {


        // Sets the case instance variable for ease of access
        this.c = c;

        setSize(new Dimension(width, height)); // Sets size to standard size
        setBackground (new Color(0, 0, 0, 0)); // Sets background colour (very temporary)
        setVisible(true); 
        setBorderPainted(false);
        setFocusPainted(false);
        
        setSelectedIcon(selectedIcon);
        setIcon(icon);
        setDisabledIcon(icon);

        // Adds an ActionListener that disables this button and queues up the "CaseSelected" event
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.addEvent(Event.caseSelection(c));
                setEnabled(false);
            }
            
        });
    }

    // Method to flip the case over to reveal the value
    public void flip() {
        
    }


}
