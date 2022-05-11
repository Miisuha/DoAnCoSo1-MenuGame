package flappybird;

import pkg2dgamesframework.QueueList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ChimneyGroup {
    
    private QueueList<Chimney> chimneys;
    private BufferedImage chimney_image, chimney_image2;
    
    public static int SIZE = 6;

    private int topChimneyY = -350;
    private int bottomChimneyY = 200;

    public Chimney getChimney(int i) {
        return chimneys.get(i);
    }

    public int getRandomY() {
        Random rand = new Random();
        int a;
        a = rand.nextInt(10);

        return a*35; 
    }

    public ChimneyGroup() {

        try {
            chimney_image = ImageIO.read(new File("access/chimney.png"));
            chimney_image2 = ImageIO.read(new File("access/chimney_.png"));
        } catch (IOException e) {}

        chimneys = new QueueList<Chimney>();  

        Chimney cn;

        for(int i = 0; i < SIZE/2; i++) {

            int DeltaY = getRandomY();

            cn = new Chimney(830+i*300, bottomChimneyY + DeltaY, 74, 400);
            chimneys.push(cn);

            cn = new Chimney(830+i*300, topChimneyY + DeltaY, 74, 400);
            chimneys.push(cn);

        }
    }

    public void reset() {
        chimneys = new QueueList<Chimney>();

        Chimney cn;

        for(int i = 0; i < SIZE/2; i++) {

            int DeltaY = getRandomY();

            cn = new Chimney(830+i*300, bottomChimneyY + DeltaY, 74, 400);
            chimneys.push(cn);

            cn = new Chimney(830+i*300, topChimneyY + DeltaY, 74, 400);
            chimneys.push(cn);

        }
    }

    public void update() {
        for(int i=0; i < SIZE; i++) {

            chimneys.get(i).update();

        } 

        if(chimneys.get(0).getPosX() < -74) {

            int DeltaY = getRandomY();

            Chimney cn;
            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX() + 300);
            cn.setPosY(bottomChimneyY + DeltaY);
            cn.setIsBehindBird(false);
            chimneys.push(cn);

            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX());
            cn.setPosY(topChimneyY + DeltaY);
            cn.setIsBehindBird(false);
            chimneys.push(cn);
        }
    }

    public void pain(Graphics2D g2) {

        for(int i = 0; i < SIZE; i++) {

            if(i%2 == 0) 
            g2.drawImage(chimney_image, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            else 
            g2.drawImage(chimney_image2, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            

        }

    }
}
