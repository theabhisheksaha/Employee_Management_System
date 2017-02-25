package mu;

import java.io.*;
import sun.audio.*;
import javax.sound.sampled.*;
import javax.sound.*;

public class Sound
{
	public static void success(){
		try{
			/*
			String songFile="success.wav";
			InputStream in =new FileInputStream(songFile);
			AudioStream audioStream=new AudioStream(in);
			AudioPlayer.player.start(audioStream);
			
			*/
			//this plays only .wav files
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Success.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void failure(){
		try{
		
		/*
		String songFile="Failure.wav";
		InputStream in =new FileInputStream(songFile);
		AudioStream audioStream=new AudioStream(in);
		AudioPlayer.player.start(audioStream);
		
		*/
		//this plays only .wav files
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Failure.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}