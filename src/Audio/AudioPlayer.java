/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audio;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * @author Miix
 */

public class AudioPlayer {
    
    private Clip clip;

    
    public AudioPlayer(String s){
        
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s));
        
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels()*2,
                    baseFormat.getSampleRate(),
                    false
            );
            
            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void play(){
    FloatControl gainControl = 
    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
        if(clip == null) return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }
    
   public void stop()
    {
        if(clip.isRunning()) clip.stop();
    }
   
   public void close(){
       stop();
       clip.close();
   }
            
    
}
