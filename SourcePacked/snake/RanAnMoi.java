package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
public class RanAnMoi extends JFrame{

    private GameScreen screen;
    
    
    public RanAnMoi() {
        this.setSize(725, 450);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake");
        screen = new GameScreen();
        this.add(screen);
        this.addKeyListener(new mHandler());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    private class mHandler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    screen.cr.setVector(ConRan.GO_UP);
                    break;
                case KeyEvent.VK_DOWN:
                    screen.cr.setVector(ConRan.GO_DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    screen.cr.setVector(ConRan.GO_LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    screen.cr.setVector(ConRan.GO_RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    GameScreen.isPlaying = !GameScreen.isPlaying;
                    if (GameScreen.isGameOver) {
                        GameScreen.isGameOver = false;
                        screen.cr.resetGame();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    GameScreen.isPlaying = !GameScreen.isPlaying;
                    if (GameScreen.isGameOver) {
                        GameScreen.isGameOver = false;
                        dispose();
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
