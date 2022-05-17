package flappybird;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.microsoft.sqlserver.jdbc.spatialdatatypes.Point;

import DBConnect.DBConnect;
import Interface.SignIn;

import java.awt.Color;


public class FlappyBird extends GameScreen {
	private String username = SignIn.usernameField.getText();
    private BufferedImage birds;
    private Animation bird_anim;
    private BufferedImage chimneys;
    DBConnect con = new DBConnect();
    
    private int highScore = con.getScoreBird(username);
    
    

    public static float g = 0.17f;

    private Bird bird;
    private Ground ground;
    private ChimneyGroup chimneyGroup;

    private int BEGIN_SCREEN = 1;
    private int GAMEPLAY_SCREEN = 2;
    private int GAMEOVER_SCREEN = 3;

    private int CurrentScreen = BEGIN_SCREEN;

    private int Point = 0;

    public FlappyBird() {
    	
        super(800, 600);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            birds = ImageIO.read(new File("access/bird_sprite.png"));

        } catch (IOException e) {}
        
        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_anim.AddFrame(f);
        
        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();
        chimneyGroup = new ChimneyGroup();

        BeginGame();
    }
    
    public static void main(String[] args) {
        new FlappyBird();
    }

    private void Reset() {
        bird.setPos(350, 250);
        bird.setVt(0);
        bird.setAlive(true);
        Point = 0;
        highScore = con.getScoreBird(username);
        chimneyGroup.reset();
    }


    @Override
    public void GAME_UPDATE(long deltaTime) {

        if(CurrentScreen == BEGIN_SCREEN) {
            Reset();
        } else if(CurrentScreen == GAMEOVER_SCREEN) {


        } else if (CurrentScreen == GAMEPLAY_SCREEN) {
            if(bird.getAlive())
                bird_anim.Update_Me(deltaTime);
                bird.update(deltaTime);
                ground.update();
                chimneyGroup.update();

            for(int i = 0; i < chimneyGroup.SIZE; i++) {
                if(bird.getRect().intersects(chimneyGroup.getChimney(i).getRect())) {
                    if(bird.getAlive()) {
                        bird.bupSound.play();
                        bird.setVt(0);
                    }
                    bird.setAlive(false);
                }
            }

            for(int i=0; i<chimneyGroup.SIZE; i++) {
                if(bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBehindBird() && i%2 == 0) {
                    Point++;
                    bird.pointSound.play();
                    chimneyGroup.getChimney(i).setIsBehindBird(true);
                }
            }
            if(bird.getPosY() + bird.getH() > ground.getYGround()) {
                CurrentScreen = GAMEOVER_SCREEN;  	
                
                con = new DBConnect();
                try {
                	
					Connection conn = con.getConnect();
					
					PreparedStatement sttm = null;
					
					String sql = "update account set scoreBird=? where username=?";
					
					if(Point > con.getScoreBird(username)) {
						sttm = conn.prepareStatement(sql);
						sttm.setInt(1, Point);
						sttm.setString(2, username);
						sttm.executeUpdate();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {

        g2.setColor(Color.decode("#b8daef"));
        g2.fillRect(0, 0, GameScreen.MASTER_WIDTH, GameScreen.MASTER_HEIGHT);

        chimneyGroup.pain(g2);
        ground.Paint(g2);

        if(bird.getIsFlying()) 
           bird_anim.PaintAnims((int) bird.getPosX() , (int) bird.getPosY(), birds, g2, 0, -0.45);
        else if(bird.getIsFlying() == false)
           bird_anim.PaintAnims((int) bird.getPosX() , (int) bird.getPosY(), birds, g2, 0, 0.45);
        else 
            bird_anim.PaintAnims((int) bird.getPosX() , (int) bird.getPosY(), birds, g2, 0, 0);

       

        if(CurrentScreen == BEGIN_SCREEN) {

        	g2.setColor(Color.red);
            g2.setFont(g2.getFont().deriveFont(18.0f));
            g2.drawString("PRESS SPACE TO PLAY GAME!", 250, 400);

        } 
        if(CurrentScreen == GAMEOVER_SCREEN) {

            g2.setColor(Color.red);
            g2.setFont(g2.getFont().deriveFont(18.0f));
            g2.drawString("PRESS SPACE TO BACK", 300, 300);

        }
        g2.setFont(g2.getFont().deriveFont(18.0f));
        g2.setColor(Color.red);
        g2.drawString("Point: " + Point, 20, 50);
 
        
       g2.drawString("High Score: " + highScore, 20, 70);

    }
       

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)   {

            if(CurrentScreen == BEGIN_SCREEN) {

                CurrentScreen = GAMEPLAY_SCREEN;

            } else if(CurrentScreen == GAMEOVER_SCREEN) {

                CurrentScreen = BEGIN_SCREEN;

            } else if(CurrentScreen == GAMEPLAY_SCREEN) {

                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
                	if(bird.getAlive()) {
                		bird.fly();
                	}
                }           
            }         
        }
    }
}
 