/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playmusic;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 *
 * @author admin
 */
public class MainClass {
    
    FileInputStream FIS;
    BufferedInputStream BIS;

    // properties
    public Player player;
    public long pauseLocaton;
    public long songTotalLength;
    public String fileLocation; 
    public long duration = 0;
    
    // method
    public void Stop() {
        if (player != null) {
            player.close();
            pauseLocaton = 0;
            MP3Player.musicLbl.setText(" ");
        } 
    }
    
    public void Pause() {
        if (player != null) {
            try {
                pauseLocaton = FIS.available();
                player.close();
            } catch (IOException ex) {
            
            }
        } 
    }

    
    public void Play(String path) {
        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
        
            songTotalLength = FIS.available();
  
        
            FileInputStream fileInputStream = null;

            try {
                fileInputStream = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                duration = Objects.requireNonNull(fileInputStream).getChannel().size() / 128;
            } catch (IOException e) {
                e.printStackTrace();
            }
          
            System.out.println("==>" + duration);
            System.out.println("==>" + songTotalLength);
            fileLocation = path + "";
            
        } catch (FileNotFoundException | JavaLayerException ex) {
            
        } catch (IOException ex) {
            
        }
        
        new Thread() {
            public void run() {
                try {
                    player.play();
                    if (player.isComplete() && MP3Player.count == 1) {
                        Play(fileLocation);
                    }
                    if (player.isComplete()) {
                        MP3Player.musicLbl.setText(" ");
                    }
                } catch (JavaLayerException ex) {

                }
            }
        }.start();
        
        new Thread() {
            public void run() {
                for (int num = 0; num <= duration; num++) {
                    try {
                        MP3Player.jp_progress.updateProgress(num, duration);
                        MP3Player.jp_progress.repaint();
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();

    }
    
    public void Resume() {
        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
     
            FIS.skip(songTotalLength - pauseLocaton);
        } catch (FileNotFoundException | JavaLayerException ex) {
            
        } catch (IOException ex) {
            
        }
        
        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
    }
    
    public static void main(String[] args) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8889/PlayMusic", "root", "root");
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Sailors");
            while(myRs.next()) {
                System.out.println(myRs.getString("sname") + ", " + myRs.getString("rating"));
            }
        } catch (Exception e) {
            
        }
    }

}
