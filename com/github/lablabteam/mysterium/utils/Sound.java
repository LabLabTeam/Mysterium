package com.github.lablabteam.mysterium.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Handles playing, stoping, and looping of sounds for the game.
 * @author Tyler Thomas
 *
 */
public class Sound {
    private Clip clip;
    private ProcessCallback cb;
    
    public Sound(String fileName, ProcessCallback cb) {
    	this.cb = cb;
    	
        try {
            File file = new File(fileName);
            //File file = this.getClass().getResourceAsStream("./default.yml");
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                
             // load the sound into memory (a Clip)
                // clip = AudioSystem.getClip();
                
                
                
                
                //AudioInputStream soundIn = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = sound.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                clip = (Clip)AudioSystem.getLine(info);
                
                
                
                
                
                
                
                
                clip.open(sound);
                
                LineListener listener = new LineListener() {
                    public void update(LineEvent event) {
        				if (event.getType() != Type.STOP) {
        					return;
        				}
        				//System.out.println("Stopped!");
        				//cb.callbackStatusAction("Stopped!");
        				// Do things!
                    }
                };
                clip.addLineListener(listener);
             
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

    // play, stop, loop the sound clip
    }
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
        //clip.close();
	}
}
