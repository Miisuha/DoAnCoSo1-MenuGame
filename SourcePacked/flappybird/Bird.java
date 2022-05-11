package flappybird;

import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

import java.awt.Rectangle;
import java.io.File;

public class Bird extends Objects {

    private float vt=0;
    private boolean isFlying = false;
    private boolean isAlive = true;

    private Rectangle rect; 
     
    public SoundPlayer flapSound, bupSound, pointSound;

    public Bird(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);

        flapSound = new SoundPlayer(new File("access/fap.wav"));
        bupSound = new SoundPlayer(new File("access/fall.wav"));
        pointSound = new SoundPlayer(new File("access/getpoint.wav"));
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void update(long deltaTime) {

        vt += FlappyBird.g;
        
        this.setPosY(this.getPosY() + vt);
        this.rect.setLocation((int) this.getPosX(), (int) this.getPosY());

        if(vt < 0) isFlying = true; else isFlying = false;
            
        
    }

    public void fly() {
        vt = -3;
        flapSound.play();
    } 

    public boolean getIsFlying() {
        return isFlying;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }
}
