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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.applet.*;

/**
 *
 * @author Miix
 */
public class Game extends JPanel implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public final String TITLE = "Flappy Assasin";
    public static String playerName = null;
    public static int scrollX = 0;
    public static int score = 0;
    public static long time = 0;
    public static long start = 0;
    JFrame frame;

    //private AudioPlayer bgMusic;

    public static enum State {
        MENU,
        SCORE,
        BEFORE,
        GAME,
        GAMEOVER
    };

    public static State state = State.MENU;

    private boolean running = false;
    private Thread thread;

    // Ładowanie obrazów 
    // Tło
    private BufferedImage image = null;

    {
        try {
            image = ImageIO.read(new File("Resources/background.png"));
        } catch (IOException e) {
            System.out.println("WRONG BACKGROUND FILE!");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
    // Napis Game Over
    private BufferedImage overImg = null;

    {
        try {
            overImg = ImageIO.read(new File("Resources/GameOver.png"));
        } catch (IOException e) {
            System.out.println("WRONG GAMEOVER FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }

    // Powołujemy do życia instancje naszych klas
    Bird bird = new Bird();
    Menu menu = new Menu();
    Enemy enemy = new Enemy();
    Score scoree = new Score();

    // KOnstruktor gry, głównie tworzy nam naszego frame'a
    public Game() {
        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        frame.addMouseListener(new UserInput());
        frame.addKeyListener(new UserInput());

    }

    // Startuje nasz program
    private synchronized void start() {
        enemy.level = 1; // Level
        //enemy.x1 = WIDTH + 100; // Nie wiem czy to jest potrzebne - nie pamiętam już xD
        
       // bgMusic = new AudioPlayer("Resources/music/level.au");
        while (true) {
            if (state == State.MENU) {
                repaint();
            }

            if (state == State.GAME) {
                if (running) {
                    return;
                }
                Bird.state = Bird.BirdState.ALIVE;
                running = true;
                thread = new Thread(this);
                thread.start();
            }
        }
    }

    // Stopuje cały program
    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
        System.exit(1);
    }

    // Główny silnik gry
    public void run() {
        
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60; //60fps
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        time = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                try {
                    update();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                updates++;
                delta--;
            }

            repaint();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }

        }

        stop();
    }

    @Override // repaint()
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (state == State.SCORE) {
            scoree.paint(g);
        }
        if (state == State.MENU) {
            menu.paint(g);
        }
        if (state == State.GAME || state == State.GAMEOVER || state == State.BEFORE) {
            g.drawImage(image, scrollX, 0, this);
            bird.paint(g);
            enemy.paint(g);
            Font font = new Font("arial", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.red);
            String s = "wynik: " + Integer.toString(score);
            String levels = "level: " + Integer.toString(enemy.level);
            String player = "Gracz: " + playerName;
            g.drawString(s, 50, 50);
            g.drawString(levels, 700, 50);
            g.drawString(player, WIDTH / 2 - 50, 50);
        }

    }

    private void update() throws FileNotFoundException {

        // Jeśli ptaszek żyje
        if (Bird.state == Bird.BirdState.ALIVE) {
            if (Bird.birdY > 0 && Bird.birdY < Game.HEIGHT) // i jeśli znajduje się na ekranie
            {
                bird.move();  // to niech się rusza
                enemy.move();
                //Przesuwanie tła
                scrollX = scrollX - 5;

                if (scrollX == -5000) {
                    scrollX = -1800;
                    enemy.level++;
                }

            } else // Jeśli wyjdzie poza ekran, ginie i jest restart
            {
                Bird.fallMusic.play();
                Bird.overMusic.play();
                Bird.dead();
                state = State.GAMEOVER;
                scrollX = 0;
                enemy.reset();
                score = 0;

            }

        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

}
