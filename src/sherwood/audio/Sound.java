package sherwood.audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound {

	RED("RED"), GREEN("GREEN"), BLUE("BLUE"), YELLOW("YELLOW");

	public static boolean MUTE = false;
	private Clip clip;

	private Sound(String fileName) {
		try {
			URL url = this.getClass().getClassLoader().getResource("res/"+fileName+".wav");
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		if (MUTE || clip.isRunning())
			return;
		clip.setFramePosition(0);
		clip.start();
	}
	
	public static void preloadSounds() {
		values();
	}
}
