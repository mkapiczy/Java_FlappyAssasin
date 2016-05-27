/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import static flappybird.Game.WIDTH;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Miix
 */
public class Enemy {

    Random rand = new Random(); // do generowania losowej wysokości przeszkód
    public int level; // Level
    public int x1=Game.WIDTH+100; // Polozenie x przeciwnika
    public int x2=Game.WIDTH+100;
    public int x3=Game.WIDTH+100;
    public int x4=Game.WIDTH+100;
    int y1 = rand.nextInt(Game.HEIGHT -100);
    int y2 = rand.nextInt(Game.HEIGHT -100);
    int y3 = rand.nextInt(Game.HEIGHT -100);
    int y4 = rand.nextInt(Game.HEIGHT -100);
    int dx2 = rand.nextInt(200);
    int dx3 = rand.nextInt(200);
    int dx4 = rand.nextInt(200);

    static int speed = -10;
    Bird bird = new Bird();

    public BufferedImage[] enemyImg = new BufferedImage[4];

    {
        for (int j = 0; j < 4; j++) {
            try {
                enemyImg[j] = ImageIO.read(new File("Resources/enemy" + (j + 1) + ".gif"));
            } catch (IOException e) {
                System.out.println("WRONG ENEMY" + j);	//Prints "WRONG BIRD" if there is an error retrieving the image
            }
        }
    }

    public Enemy() {
    }

    public void paint(Graphics g) {

        switch (level) {
            case 1: {

                g.drawImage(enemyImg[0], x1, y1, null);
                break;
            }
            case 2: {
                g.drawImage(enemyImg[0], x1, y1, null);
                g.drawImage(enemyImg[1], x2, y2, null);
                break;
            }
            case 3: {
                g.drawImage(enemyImg[0], x1, y1, null);
                g.drawImage(enemyImg[1], x2 , y2, null);
                g.drawImage(enemyImg[2], x3, y3, null);
                break;
            }
            case 4: {
                g.drawImage(enemyImg[0], x1, y1, null);
                g.drawImage(enemyImg[1], x2 , y2, null);
                g.drawImage(enemyImg[2], x3 , y3, null);
                g.drawImage(enemyImg[3], x4 , y4, null);
                break;
            }
            case 5: {
                speed = -13;
                g.drawImage(enemyImg[0], x1, y1, null);
                g.drawImage(enemyImg[1], x2 , y2, null);
                g.drawImage(enemyImg[2], x3 , y3, null);
                g.drawImage(enemyImg[3], x4 , y4, null);
                break;
            }
            case 6: {
                speed = -15;
                g.drawImage(enemyImg[0], x1, y1, null);
                g.drawImage(enemyImg[1], x2 , y2, null);
                g.drawImage(enemyImg[2], x3 , y3, null);
                g.drawImage(enemyImg[3], x4 , y4, null);
                break;
            }
            case 7: {
                speed = -18;
                 g.drawImage(enemyImg[0], x1, y1, null);
                g.drawImage(enemyImg[1], x2 , y2, null);
                g.drawImage(enemyImg[2], x3 , y3, null);
                g.drawImage(enemyImg[3], x4 , y4, null);
                break;
            }

        }
    }

    public void move() {

// Ruch przeciwników
        x1 += speed;
        x2 += speed;
        x3 += speed;
        x4 += speed;
        Rectangle enemy1Bounds = new Rectangle(x1, y1, enemyImg[0].getWidth(), enemyImg[0].getHeight());
        Rectangle enemy2Bounds = new Rectangle(x2 + dx2, y2, enemyImg[0].getWidth(), enemyImg[0].getHeight());
        Rectangle enemy3Bounds = new Rectangle(x3 + dx3, y3, enemyImg[0].getWidth(), enemyImg[0].getHeight());
        Rectangle enemy4Bounds = new Rectangle(x4 + dx4, y4, enemyImg[0].getWidth(), enemyImg[0].getHeight());
        
 // Wykrywanie kolizji
        switch (level) {
            case 1: {
                if ((enemy1Bounds.intersects(bird.getBounds()))) {
                    Bird.dead();
                    Bird.fallMusic.play();
                    Bird.overMusic.play();
                    reset();
                    Game.state = Game.State.GAMEOVER;
                    Game.scrollX = 0;
                }
                if (enemy1Bounds.x == Game.WIDTH / 2) {
                    Game.score++;
                }
                
                if (x1 <= 0 - Game.WIDTH) {
            x1 = Game.WIDTH;
            y1 = rand.nextInt(Game.HEIGHT-100);
                }
                break;
            }

            case 2: {
                if ((enemy1Bounds.intersects(bird.getBounds())) || (enemy2Bounds.intersects(bird.getBounds()))) {
                    Bird.dead();
                    Bird.fallMusic.play();
                    Bird.overMusic.play();
                    reset();
                    Game.state = Game.State.GAMEOVER;
                    Game.scrollX = 0;
                }
                if (enemy1Bounds.x == Game.WIDTH / 2) {
                    Game.score++;
                }

                if ((enemy2Bounds.x) == Game.WIDTH / 2 + dx2) {
                    Game.score++;
                }
                
                if (x1 <= 0 - Game.WIDTH) {
            x1 = Game.WIDTH;
            y1 = rand.nextInt(Game.HEIGHT-100);
        }
        if (x2 <= 0 - Game.WIDTH) {
            x2 = Game.WIDTH;
            y2 = rand.nextInt(Game.HEIGHT-100);
            dx2 = rand.nextInt(300);

        }
                break;
            }
            case 3: {
                if ((enemy1Bounds.intersects(bird.getBounds())) || (enemy2Bounds.intersects(bird.getBounds())) || (enemy3Bounds.intersects(bird.getBounds()))) {
                    Bird.dead();
                    Bird.fallMusic.play();
                    Bird.overMusic.play();
                    reset();
                    Game.state = Game.State.GAMEOVER;
                    Game.scrollX = 0;
                }
                if (enemy1Bounds.x == Game.WIDTH / 2) {
                    Game.score++;
                }

                if ((enemy2Bounds.x) == Game.WIDTH / 2 + dx2) {
                    Game.score++;
                }

                if ((enemy3Bounds.x) == Game.WIDTH / 2 + dx3) {
                    Game.score++;
                }
                
                 if (x1 <= 0 - Game.WIDTH) {
            x1 = Game.WIDTH;
            y1 = rand.nextInt(Game.HEIGHT-100);
        }
        if (x2 <= 0 - Game.WIDTH) {
            x2 = Game.WIDTH;
            y2 = rand.nextInt(Game.HEIGHT-100);
            dx2 = rand.nextInt(300);

        }
        if (x3 <= 0 - Game.WIDTH) {
            x3 = Game.WIDTH;
            y3 = rand.nextInt(Game.HEIGHT-100);
            dx3 = rand.nextInt(300);
        }
                break;
            }
            case 4: {
                if ((enemy1Bounds.intersects(bird.getBounds())) || (enemy2Bounds.intersects(bird.getBounds())) || (enemy3Bounds.intersects(bird.getBounds())) || (enemy4Bounds.intersects(bird.getBounds()))) {
                    Bird.dead();
                    Bird.fallMusic.play();
                    Bird.overMusic.play();
                    reset();
                    Game.state = Game.State.GAMEOVER;
                    Game.scrollX = 0;

                }
                if (enemy1Bounds.x == Game.WIDTH / 2) {
                    Game.score++;
                }

                if ((enemy2Bounds.x) == Game.WIDTH / 2 + dx2) {
                    Game.score++;
                }

                if ((enemy3Bounds.x) == Game.WIDTH / 2 + dx3) {
                    Game.score++;
                }
                if ((enemy4Bounds.x) == Game.WIDTH / 2 + dx4) {
                    Game.score++;
                }
                 if (x1 <= 0 - Game.WIDTH) {
            x1 = Game.WIDTH;
            y1 = rand.nextInt(Game.HEIGHT-100);
        }
        if (x2 <= 0 - Game.WIDTH) {
            x2 = Game.WIDTH;
            y2 = rand.nextInt(Game.HEIGHT-100);
            dx2 = rand.nextInt(300);

        }
        if (x3 <= 0 - Game.WIDTH) {
            x3 = Game.WIDTH;
            y3 = rand.nextInt(Game.HEIGHT-100);
            dx3 = rand.nextInt(300);
        }
                if (x4 <= 0 - Game.WIDTH) {
            x4 = Game.WIDTH;
            y4 = rand.nextInt(Game.HEIGHT-100);
            dx4 = rand.nextInt(300);
        }
                break;
            }
            case 5: {
                if ((enemy1Bounds.intersects(bird.getBounds())) || (enemy2Bounds.intersects(bird.getBounds())) || (enemy3Bounds.intersects(bird.getBounds())) || (enemy4Bounds.intersects(bird.getBounds()))) {
                    Bird.dead();
                    Bird.fallMusic.play();
                    Bird.overMusic.play();
                    reset();
                    Game.state = Game.State.GAMEOVER;
                    Game.scrollX = 0;
                }
                speed = -12;

               if (enemy1Bounds.x == Game.WIDTH / 2) {
                    Game.score++;
                }

                if ((enemy2Bounds.x) == Game.WIDTH / 2 + dx2) {
                    Game.score++;
                }

                if ((enemy3Bounds.x) == Game.WIDTH / 2 + dx3) {
                    Game.score++;
                }
                if ((enemy4Bounds.x) == Game.WIDTH / 2 + dx4) {
                    Game.score++;
                }
                if (x1 <= 0 - Game.WIDTH) {
            x1 = Game.WIDTH;
            y1 = rand.nextInt(Game.HEIGHT-100);
        }
        if (x2 <= 0 - Game.WIDTH) {
            x2 = Game.WIDTH;
            y2 = rand.nextInt(Game.HEIGHT-100);
            dx2 = rand.nextInt(300);

        }
        if (x3 <= 0 - Game.WIDTH) {
            x3 = Game.WIDTH;
            y3 = rand.nextInt(Game.HEIGHT-100);
            dx3 = rand.nextInt(300);
        }
                if (x4 <= 0 - Game.WIDTH) {
            x4 = Game.WIDTH;
            y4 = rand.nextInt(Game.HEIGHT-100);
            dx4 = rand.nextInt(300);
        }
                break;
            }
        }
 
    }

    public void reset() {
        x1 = Game.WIDTH + 100;
        level = 1;
    }

}
