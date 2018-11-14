/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playmusic;

import sun.audio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author admin
 */
public class Sound {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(200, 200);
        JButton button = new JButton("Click me");
        frame.add(button);
        button.addActionListener(new AL());
        frame.show(true);
    }
    
    public static class AL implements ActionListener {
        public final void actionPerformed(ActionEvent e) {
            music();
            System.out.println("click");
        }
    }
    
    public static void music() {
//        AudioPlayer MGP = AudioPlayer.player;
//        AudioStream BGM;
//        AudioData MD;
//        ContinuousAudioDataStream loop = null;
//        
//        try {
//            BGM = new AudioStream(new FileInputStream("BÍCH-PHƯƠNG-Bùa-Yêu-Official-M-V.wav"));
//            MD = BGM.getData();
//        } catch (IOException ex) {
//            
//        }        
//         MGP.start(loop);

        String bip = "/Users/admin/Desktop/Java/PlayMusic/music/BÍCH-PHƯƠNG-Bùa-Yêu-Official-M-V.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();

    }
    
}
