package data.graphics.guiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import data.graphics.Event;
import data.graphics.EventManager;
import data.main.Case;

public class CaseGraphics extends JButton {
    
    public static int width = 170;
    public static int height = 60;
    Case c;
    
    public CaseGraphics(Case c) {
        this.c = c;
        System.out.println(c);

        setSize(new Dimension(width, height));
        setBackground(Color.red);
        setVisible(true);

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.addEvent(Event.caseSelection(c));
                setEnabled(false);
            }
            
        });
    }

    public void flip() {
        
    }


}
