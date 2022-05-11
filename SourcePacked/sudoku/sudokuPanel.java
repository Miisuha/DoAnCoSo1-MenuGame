package sudoku;

import java.awt.*;
import javax.swing.JPanel;

public class sudokuPanel extends JPanel {

    private sudokuPuzzle puzzle;

    public sudokuPanel() {
        this.setPreferredSize(new Dimension(540, 450));
        this.puzzle = sudokuGenerator.generateRandomSudoku(sudokuPuzzleType.NINEBYNINE);
        System.out.println(this.puzzle);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f)); // white

        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(new Color(0.0f, 0.0f, 0.0f)); // black
        int slotWidth = this.getWidth() / 9;
        int slotHeight = this.getHeight() / 9;

        for(int i = 0; i <= this.getWidth(); i += slotWidth) {
            if((i / slotWidth) % 3 == 0) {
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(i, 0, i, this.getHeight());
            } 
            else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(i, 0, i, this.getHeight());
            }
        }

        for(int j = 0; j <= this.getHeight(); j += slotHeight) {
            if((j / slotHeight) % 3 == 0) {
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(0, j, this.getWidth(), j);
            } 
            else { 
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, j, this.getWidth(), j);
            }
        }
    }
    
}
