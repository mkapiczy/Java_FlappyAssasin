/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Audio.AudioPlayer;

/**
 *
 * @author Miix
 */
public class Bird extends JPanel {

  
    private static int birdX = Game.WIDTH / 2;
    public static int birdY = Game.HEIGHT / 2;
    static double gravity = 1;
    static double speed = 2;
    static int score = 0;
    private BufferedImage bird;
    private static final AudioPlayer jumpMusic = new AudioPlayer("Resources/music/jump.au");
    public static AudioPlayer fallMusic = new AudioPlayer("Resources/music/fall.au");
    public static AudioPlayer overMusic = new AudioPlayer("Resources/music/gameover.au");
    public static enum BirdState {

        DEAD,
        ALIVE
    }
    public static BirdState state = BirdState.DEAD;

    public static BufferedImage birdImg = null;
    {
        try {
            birdImg = ImageIO.read(new File("Resources/bird.gif"));
        } catch (IOException e) {
            System.out.println("WRONG BIRD");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
    
        private BufferedImage overImg = null;

    {
        try {
           overImg = ImageIO.read(new File("Resources/GameOver.png"));
        } catch (IOException e) {
            System.out.println("WRONG MENU FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }

    private BufferedImage readyImg = null;

    {
        try {
           readyImg = ImageIO.read(new File("Resources/GetReady.png"));
        } catch (IOException e) {
            System.out.println("WRONG MENU FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
    
    private BufferedImage spacjaImg = null;

    {
        try {
           spacjaImg = ImageIO.read(new File("Resources/spacja.gif"));
        } catch (IOException e) {
            System.out.println("WRONG SPACJA FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
    public Bird() {

    }

    public static void jump() {
        speed = -15;
        jumpMusic.play();
    }

    public void move() {
        if (state == BirdState.ALIVE) {
            if (birdY > 0 && birdY < Game.HEIGHT) {
                speed += gravity;
                birdY += speed;
            } else {
                dead();
            }
        }
    }

    public static void dead() {
        UserInput.bgMusic.stop();
        birdY = Game.HEIGHT / 2;
        state = BirdState.DEAD;
        String s = Game.playerName + " : " + Game.score;
        Score.saveScore(s, Game.score);
        Game.score = 0;
    }

    public static void alive() {
        birdX = Game.WIDTH / 2;
        birdY = Game.HEIGHT / 2;
        speed = 2;
        state = BirdState.ALIVE;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(birdImg, birdX, birdY, null);

        if (Game.state == Game.State.GAMEOVER) {
       g.drawImage(overImg, Game.WIDTH/2-overImg.getWidth()/2,Game.HEIGHT/2-200, null);
       g.drawImage(spacjaImg, Game.WIDTH/2-spacjaImg.getWidth()/2,Game.HEIGHT/2-100, null);
       
        }
        
        if(Game.state == Game.State.BEFORE)
        {
            g.drawImage(spacjaImg, Game.WIDTH/2-spacjaImg.getWidth()/2,Game.HEIGHT/2-100, null);
            g.drawImage(readyImg,Game.WIDTH/2-overImg.getWidth()/2,Game.HEIGHT/2-200, null);
        }
            
    }
    
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(birdX, birdY, birdImg.getWidth(),birdImg.getHeight());
    }

}
