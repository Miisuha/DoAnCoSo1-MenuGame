package snake;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
public class GameScreen extends JPanel implements Runnable {
    private Thread thr;
    static int[][] bg = new int[20][20];
    ConRan cr;
    static int padding = 10;
    static int Width = 400;
    static int Height = 400;
    static boolean isPlaying = false;
    static boolean isGameOver = false;
    static boolean enableTextStart = true;
            
    public GameScreen() {
        cr = new ConRan();
        Images.loadData();
        bg[cr.newMoi().x][cr.newMoi().y] = 2;      
        thr = new Thread(this);
        thr.start();
    }
    
    @Override
    public void paint(Graphics g) {
        paintBg(g);
        cr.veRan(g);
        paintBorder(g);
        
        if (!isPlaying) {
            if (enableTextStart) {
                g.setColor(Color.red);
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("PRESS SPACE TO PLAY GAME!", 70, 200);
            }
        }
        if (isGameOver) {
            if (enableTextStart) {
                g.setColor(Color.red);
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("GAMEOVER!", 145, 160);
                cr.delay = 200;
                cr.score = 0;
                cr.level = 1;
                cr.maxLength = 10;
            }
        }
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawString("Level: " + cr.level, 450, 30);
        g.drawString("Score: " + cr.score, 450, 60);
        g.drawString("Top 10:", 450, 90);
    }

    @Override
    public void run() {
        long tText = 0;
        while (true) {
            if (System.currentTimeMillis() - tText > 500) {
                enableTextStart = !enableTextStart;
                tText = System.currentTimeMillis();
            }
            if (isPlaying) {
                cr.Update();
            }
            repaint();
            try {
                Thread.sleep(34);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    public void paintBg(Graphics g) {
        g.setColor(Color.decode("#98d68b"));
        g.fillRect(0, 0, GameScreen.Width + GameScreen.padding*2 + 300, GameScreen.Height + GameScreen.padding*2);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (bg[i][j] == 2) {
                    g.drawImage(Images.Worm.getCurrentImage(), i * 20 + padding, j * 20 - 5 + padding, null);
                    return;
                }
            }
        }
    }
    
    public void paintBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(0, 0, GameScreen.Width + GameScreen.padding*2, GameScreen.Height + GameScreen.padding*2);
        g.drawRect(1, 1, GameScreen.Width + GameScreen.padding*2 - 2, GameScreen.Height + GameScreen.padding*2 - 2);
        g.drawRect(2, 2, GameScreen.Width + GameScreen.padding*2 - 4, GameScreen.Height + GameScreen.padding*2 - 4);
        g.drawRect(0, 0, GameScreen.Width + GameScreen.padding*2 + 300, GameScreen.Height + GameScreen.padding*2);
        g.drawRect(1, 1, GameScreen.Width + GameScreen.padding*2 - 2 + 300, GameScreen.Height + GameScreen.padding*2 - 2);
        g.drawRect(2, 2, GameScreen.Width + GameScreen.padding*2 - 4 + 300, GameScreen.Height + GameScreen.padding*2 - 4);
    }
    
}
