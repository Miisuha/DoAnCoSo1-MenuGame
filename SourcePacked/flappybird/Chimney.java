package flappybird;

import pkg2dgamesframework.Objects;
import java.awt.Rectangle;

public class Chimney extends Objects {

    private Rectangle rect;

    private Boolean isBehindBird = false;

    public Chimney(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
    }
    
    public void update() { 
        //  toc đo cua chim
        setPosX(getPosX() - 2);
        // end 
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setIsBehindBird(boolean b) {
        isBehindBird = b;
    }

    public boolean getIsBehindBird() {
        return isBehindBird;
    }
}
