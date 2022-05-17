package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class ConRan {
    ArrayList<Integer> rX;
    ArrayList<Integer> rY;
    
    long t1 = 0;
    long t2 = 0;
    long delay = 200;
    public static final int GO_UP = 1;
    public static final int GO_DOWN = -1;
    public static final int GO_LEFT = 2;
    public static final int GO_RIGHT = -2;
    
    int vector;
    int nextVector;
    
    int score = 0;
    int level = 1;
    int maxLength = 10;
    
    public void setVector(int vector) {
        if(GameScreen.isPlaying && this.vector != -vector) {
            this.nextVector = vector;
        }
    }
    
    public ConRan() {
        this.vector = ConRan.GO_RIGHT;
        this.nextVector = this.vector;
        rX = new ArrayList<Integer>();
        rY = new ArrayList<Integer>();
        this.growUp(6, 6);
        this.growUp(5, 6);
        this.growUp(4, 6);
        this.growUp(3, 6);
    }
    
    public void resetGame() {
        rX.clear();
        rY.clear();
        this.growUp(6, 6);
        this.growUp(5, 6);
        this.growUp(4, 6);
        this.growUp(3, 6);
        this.vector = ConRan.GO_RIGHT;
        this.nextVector = this.vector;
    }
    
    public void veRan(Graphics g) {
        g.setColor(Color.yellow);
        for (int i = 1; i < rX.size(); i++) {
            g.drawImage(Images.imageBody, rX.get(i) * 20 + GameScreen.padding, rY.get(i) * 20 + GameScreen.padding, null);
        }
        int x0, y0;
        x0 = rX.get(0) * 20 - 5 + GameScreen.padding;
        y0 = rY.get(0) * 20 - 5 + GameScreen.padding;
        switch(vector) {
                case ConRan.GO_UP:
                    g.drawImage(Images.HeadGoUp.getCurrentImage(), x0, y0, null);
                    break;
                case ConRan.GO_DOWN:
                    g.drawImage(Images.HeadGoDown.getCurrentImage(), x0, y0, null);
                break;
                case ConRan.GO_LEFT:
                    g.drawImage(Images.HeadGoLeft.getCurrentImage(), x0, y0, null);
                break;
                case ConRan.GO_RIGHT:
                    g.drawImage(Images.HeadGoRight.getCurrentImage(), x0, y0, null);
                break;
            }
    }
    
    public void Update() {
        
        if (rX.size() == maxLength) {
            GameScreen.isPlaying = false;
            resetGame();
            level++;
            delay *= 0.8;
            maxLength += 5;
        }
        
        for (int i = 2; i < rX.size(); i++) {
            if (rX.get(0) == rX.get(i) && rY.get(0) == rY.get(i)) {
                GameScreen.isGameOver = true;
                GameScreen.isPlaying = false;
            }
        }
        
        if (System.currentTimeMillis()-t1 >  delay) {
            if(GameScreen.bg[rX.get(0)][rY.get(0)] == 2) {
                this.growUp(rX.get(0), rY.get(0));
                this.score += 100*level;
                GameScreen.bg[rX.get(0)][rY.get(0)] = 0;
                Point tp = newMoi();
                GameScreen.bg[tp.x][tp.y] = 2;
//                if (delay > 80) {
//                    delay -= 10;
//                }
            }
            
            for (int i = rX.size() - 1; i > 0; i--) {
                rX.set(i, rX.get(i-1));
                rY.set(i, rY.get(i-1));
            }
            Images.Worm.Update();
            vector = nextVector;
            switch(vector) {
                case ConRan.GO_UP:
                    rY.set(0, rY.get(0)-1);
                    Images.HeadGoUp.Update();
                    break;
                case ConRan.GO_DOWN:
                    rY.set(0, rY.get(0)+1);
                    Images.HeadGoDown.Update();
                break;
                case ConRan.GO_LEFT:
                    rX.set(0, rX.get(0)-1);
                    Images.HeadGoLeft.Update();
                break;
                case ConRan.GO_RIGHT:
                    rX.set(0, rX.get(0)+1);
                    Images.HeadGoRight.Update();
                break;
            }
            
            if(rX.get(0) < 0) rX.set(0, 19);
            if(rY.get(0) < 0) rY.set(0, 19);
            if(rX.get(0) > 19) rX.set(0, 0);
            if(rY.get(0) > 19) rY.set(0, 0);
            
            t1 = System.currentTimeMillis();
        }
    }
    
    public void growUp(int x, int y) {
        rX.add(x);
        rY.add(y);
    }
    
    public Point newMoi() {
        Random r = new Random();
        int x, y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        } while(checkMoi(x, y));
        return new Point(x, y);
    }
    
    public boolean checkMoi(int x, int y) {
        for (int i = 0; i < rX.size(); i++) {
            if (rX.get(i) == x && rY.get(i) == y)
                return true;
        }
        return false;
    }
}

