package data.graphics.guiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import data.graphics.PanelGraphics;
import data.main.Board;
import data.main.Case;

public class BoardGraphics extends PanelGraphics {

    Board board; 

    public BoardGraphics(Board board) {
        this.board = board;
        initVars();
    }

    protected void initVars() {

        // Adds all of the CaseGraphics to this panel
        for (ArrayList<Case> row:board) {
            for (Case c:row) {
                add(c.graphics);
            }
        }

        setSize(new Dimension(1920*3/4, 1080 * 2/3)); // Sets the size to 75% of the width and 67% of the height
        setVisible(true);
        setBackground(Color.BLUE); // Sets the background colour (Very temorary)

        setLayout(null); // Sets the layout to null to allow for absolute element placement
    }

    // Might remove depending on how the rest of the process goes
    @Override
    public void paint() {}

    // On each new frame, this will be called to align the cases
    @Override
    protected void update() {
        alignCases(); 
    }

    public void alignCases() {
        int[] margins = {50, 50, 50, 50}; // Sets the margins as: {left, top, right, bottom}

        int minX = margins[0]; // Sets the minimum X value depending on the margins
        int minY = margins[1]; // ^^ but with "Y"
        int maxX = getWidth() - margins[2]; // Gets the max X value
        int maxY = getHeight() - margins[3]; // ^^ but with "Y"
        int nWidth = maxX - minX; // Gets the new width
        int nHeight = maxY - minY; // Gets the new height

        int xGap, yGap;
        int x, y;

        int numRows = board.size(); // Gets the number of rows
        int numCols; // Variable to be set per row of the number of colomns/elements in each row

        // Sets the vertical gap between cases
        yGap = nHeight - numRows * CaseGraphics.height; 
        yGap /= numRows + 1;

        for (int row = 0; row < numRows; row++) {
            
            numCols = board.get(row).size();

            // Sets the horizontal gap between cases
            xGap = nWidth - (numCols * CaseGraphics.width);
            xGap /= numCols + 1;

            for (int col = 0; col < numCols; col++) {

                // Gets the X value
                x = minX;
                x += (col+1) * xGap;
                x += col * CaseGraphics.width;

                // Gets the Y value
                y = minY;
                y += (row+1) * yGap;
                y += row * CaseGraphics.height;

                setCaseLoc(row, col, x, y); // Sets the case location
            }
        }

    }

    private void setCaseLoc(int row, int col, int x, int y) {
        //System.out.println(row + " , " + col + " , " + x + " , " + y);
        board.getCaseAt(row, col).graphics.setLocation(x, y);
    }

    public void lockButtons() {
        for (ArrayList<Case> row:board) {
            for (Case c: row) {
                c.graphics.setEnabled(false);
            }
        }
    }
     
}
