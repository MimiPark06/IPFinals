import java.io.*;
import java.io.File;
import javax.sound.sampled.*;


public class Sound {

  public static void main(String[] args){
    try{
      AudioInputStream bg = AudioSystem.getAudioInputStream(new File("Ambience.wav"));

      Clip clip = AudioSystem.getClip();
      clip.open(bg);

      clip.start();

      for (int i=0; i<30000; i++)
      System.out.println("the forest");

    }catch(Exception e){e.printStackTrace();}
  }
}