/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Audio.AudioPlayer;
/**
 *
 * @author Miix
 */
public class UserInput implements MouseListener, KeyListener {

    Menu menu = new Menu();
    Bird bird = new Bird();
    Enemy enemy = new Enemy();
    public static AudioPlayer bgMusic = new AudioPlayer("Resources/music/level.au");
    public static AudioPlayer beforeMusic = new AudioPlayer("Resources/music/before2.au");

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mousex = e.getX();
        int mousey = e.getY();

        if (Game.state == Game.State.MENU) {
            // PLAY
            if (mousex >= menu.playButton.x && mousex <= menu.playButton.x + menu.playButton.width) {
                if (mousey >= menu.playButton.y + 40 && mousey <= menu.playButton.y + menu.playButton.height + 40) {
                    PlayerNameFrame name = new PlayerNameFrame();
                    name.setLocationRelativeTo(null);
                    name.setVisible(true);
                }
            }

            // QUIT
            if (mousex >= menu.quitButton.x && mousex <= menu.quitButton.x + menu.quitButton.width) {
                if (mousey >= menu.quitButton.y + 40 && mousey <= menu.quitButton.y + menu.quitButton.height + 40) {
                    System.exit(0);
                }
            }

            if (mousex >= menu.scoreButton.x && mousex <= menu.scoreButton.x + menu.scoreButton.width) {
                if (mousey >= menu.scoreButton.y + 40 && mousey <= menu.scoreButton.y + menu.scoreButton.height + 40) {
                    Game.state = Game.State.SCORE;
                }
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        // SPACJA
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
            if (Game.state == Game.State.GAME) {
                Bird.jump();
            } else if (Game.state == Game.State.GAMEOVER) {
                Game.time = Game.start;
                Game.state = Game.State.GAME;
                Bird.alive();
                Menu.menuMusic.stop();
                Bird.overMusic.stop();
                Bird.fallMusic.stop();
                bgMusic.play();
            } else if (Game.state == Game.State.BEFORE) {
                Game.state = Game.State.GAME;
                Bird.state = Bird.BirdState.ALIVE;
                bird.alive();
                enemy.reset();
                Bird.fallMusic.stop();
                UserInput.beforeMusic.stop();
                bgMusic.play();
            }
        } 
        //ESCAPE
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { 
            if (Game.state == Game.State.GAME) {
                Bird.dead();
                enemy.reset();
                Game.state = Game.State.MENU;
                bgMusic.stop();
                Menu.menuMusic.play();
            } else if (Game.state == Game.State.GAMEOVER) {
                Bird.dead();
                enemy.reset();
                Game.state = Game.State.MENU;
                UserInput.beforeMusic.stop();
                Bird.fallMusic.stop();
                Menu.menuMusic.play();

            } else if (Game.state == Game.State.SCORE) {
                Game.state = Game.State.MENU;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
