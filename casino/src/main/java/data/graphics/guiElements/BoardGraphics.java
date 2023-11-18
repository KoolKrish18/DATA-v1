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

        for (ArrayList<Case> row:board) {
            for (Case c:row) {
                add(c.graphics);
            }
        }

        System.out.println("Component Count: " + getComponentCount());
        System.out.println(board);

        setSize(new Dimension(1920*3/4, 1080 * 2/3));;
        setVisible(true);
        setBackground(Color.BLUE);

        setLayout(null);

        
    }

    @Override
    public void paint() {
        
    }

    @Override
    protected void update() {
        alignCases();
    }

    public void alignCases() {
        int[] margins = {50, 50, 50, 50};

        int minX = margins[0];
        int minY = margins[1];
        int maxX = getWidth() - margins[2];
        int maxY = getHeight() - margins[3];
        int nWidth = maxX - minX;
        int nHeight = maxY - minY;

        int xGap;
        int yGap;

        int x;
        int y;

        int rowSize = board.size();
        int colSize;

        yGap = nHeight - rowSize * CaseGraphics.height;
        yGap /= rowSize + 1;

        for (int row = 0; row < rowSize; row++) {
            
            colSize = board.get(row).size();

            xGap = nWidth - (colSize * CaseGraphics.width);
            xGap /= colSize + 1;

            for (int col = 0; col < colSize; col++) {

                x = minX;
                x += (col+1) * xGap;
                x += col * CaseGraphics.width;

                y = minY;
                y += (row+1) * yGap;
                y += row * CaseGraphics.height;

                setCaseLoc(row, col, x, y);
            }
        }

    }

    private void setCaseLoc(int row, int col, int x, int y) {
        //System.out.println(row + " , " + col + " , " + x + " , " + y);
        board.getCaseAt(row, col).graphics.setLocation(x, y);
    }
     
}
