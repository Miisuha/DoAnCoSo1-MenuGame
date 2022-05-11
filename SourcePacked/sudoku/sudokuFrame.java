package sudoku;

import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

public class sudokuFrame extends JFrame {

    private sudokuPanel sPanel;
    
    public sudokuFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku");
        this.setMinimumSize(new Dimension(800, 600));

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Game");
        JMenu newGame = new JMenu("New Game");

        JMenuItem Six = new JMenuItem("6x6");
        JMenuItem Nine = new JMenuItem("9x9");
        JMenuItem Twelve = new JMenuItem("12x12");
        newGame.add(Six); newGame.add(Nine); newGame.add(Twelve);

        file.add(newGame);
        menuBar.add(file);
        this.setJMenuBar(menuBar);
        
        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new FlowLayout());
        windowPanel.setPreferredSize(new Dimension(800, 600));

        sPanel = new sudokuPanel();
        windowPanel.add(sPanel);

        this.add(windowPanel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                sudokuFrame frame = new sudokuFrame();
                frame.setVisible(true);

            }

            
        });
    }
}
