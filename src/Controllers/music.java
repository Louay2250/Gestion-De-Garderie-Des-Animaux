/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Azertyy
 */
public class music {   
    public static void playSound(String filePath) {
       try {
            File soundFile = new File(music.class.getResource(filePath).toURI());
            Media media = new Media(soundFile.toURI().toString());
            MediaPlayer MP = new MediaPlayer(media);
            MP.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}
