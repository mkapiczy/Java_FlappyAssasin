/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class Menu extends JPanel {

    int highscore;
    public static AudioPlayer menuMusic = new AudioPlayer("Resources/music/menu.au");
    

    private BufferedImage menuImg = null;

    {
        try {
            menuImg = ImageIO.read(new File("Resources/menu.png"));
        } catch (IOException e) {
            System.out.println("WRONG MENU FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
        private BufferedImage buttonImg = null;

    {
        try {
           buttonImg = ImageIO.read(new File("Resources/Button.png"));
        } catch (IOException e) {
            System.out.println("WRONG BUTTON FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
    
      private BufferedImage logoImg = null;

    {
        try {
           logoImg = ImageIO.read(new File("Resources/logo2.png"));
        } catch (IOException e) {
            System.out.println("WRONG LOGO FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
    
    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 90, Game.HEIGHT / 2 - 100, buttonImg.getWidth(),buttonImg.getHeight());
    public Rectangle scoreButton = new Rectangle(Game.WIDTH / 2 - 90, Game.HEIGHT / 2,  buttonImg.getWidth(),buttonImg.getHeight());
    public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 90, Game.HEIGHT / 2 + 100,  buttonImg.getWidth(),buttonImg.getHeight());
   

   
    public Menu() {
        Menu.menuMusic.play();
        

    }

    @Override
    public void paint(Graphics g) {

          Enemy enemy = new Enemy();
          Bird bird = new Bird();
    
        g.drawImage(menuImg, 0, 0, this);
     
       /* Font font = new Font("arial", Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Flappy Assasin!", Game.WIDTH / 2 - 185, HEIGHT + 100);*/
        g.drawImage(logoImg, Game.WIDTH / 2 - logoImg.getWidth()/2, HEIGHT + 25, null);

        Font font2 = new Font("arial", Font.BOLD, 30);
        g.setFont(font2);
         g.setColor(Color.red);
         g.drawImage(buttonImg,playButton.x,playButton.y,null);
         g.drawImage(buttonImg,scoreButton.x,scoreButton.y,null);
        g.drawImage(buttonImg,quitButton.x,quitButton.y,null);
        g.drawString("Graj", playButton.x + 60, playButton.y + 40);
        g.drawString("Wyniki", scoreButton.x + 50, scoreButton.y + 40);
        g.drawString("Wyjdź", quitButton.x + 50, quitButton.y + 40);
        
        // Ikonki postaci
        g.drawImage(bird.birdImg,Game.WIDTH/2-10,125,null);
        g.drawImage(enemy.enemyImg[0],50,50,null);
        g.drawImage(enemy.enemyImg[1],Game.WIDTH-100,50,null);
        g.drawImage(enemy.enemyImg[2],Game.WIDTH-100,Game.HEIGHT-150,null);
        g.drawImage(enemy.enemyImg[3],50,Game.HEIGHT-150,null);
       
    }

}
