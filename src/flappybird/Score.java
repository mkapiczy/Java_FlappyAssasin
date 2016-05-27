/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Miix
 */
public class Score extends JPanel{

    static File file = new File("score.txt");
    static  File file2 = new File("score2.txt");
    private List<String> score = new ArrayList<>();
    int i=0;
    
     private BufferedImage scoreImg = null;
    {
        try {
            scoreImg = ImageIO.read(new File("Resources/menu.png"));
        } catch (IOException e) {
            System.out.println("WRONG MENU FILE");	//Prints "WRONG BIRD" if there is an error retrieving the image
        }
    }
    
    
    
    public static int getHighscore() throws FileNotFoundException {
  
        Scanner in = new Scanner(file2);

        String last = null, line = null;
        while(in.hasNextLine())
        {
        line = in.nextLine();
            last = line;
        }
        int highscore = Integer.parseInt(last);
        in.close();
        return highscore;  
    }
    /*
    public void wczytaj() throws FileNotFoundException
    {
        i=0;
     Scanner in = new Scanner(file);
     while (in.hasNextLine())
     {
     i++;
     String zdanie = in.nextLine();   
     score.add(zdanie);
     }
    }*/

    @Override
    public void paint(Graphics g){
      
 
            g.drawImage(scoreImg, 0, 0, this);
            Font font = new Font("arial", Font.BOLD, 50);
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Wyniki: ", Game.WIDTH / 2 - 80, HEIGHT + 100);
            Font font2 = new Font("arial", Font.BOLD, 15);
            g.setFont(font2);
            g.setColor(Color.red);
            
            try{
            Scanner in = new Scanner(file);
            String line = null;
            int j = 0;
            while(in.hasNextLine())
            {
            line = in.nextLine();
                j++;
                g.drawString(line,Game.WIDTH/2-50, Game.HEIGHT/6+20+(15*j));
            }   } catch (FileNotFoundException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public static void saveScore(String s, int i) {
        
        try{

            FileWriter fstream = new FileWriter(file,true);
            BufferedWriter fbw = new BufferedWriter(fstream);
            fbw.write(s);
            fbw.newLine();
            fbw.close();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
