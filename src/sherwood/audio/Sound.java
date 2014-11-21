package sherwood.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public enum Sound {

    RED("RED"), GREEN("GREEN"), BLUE("BLUE"), YELLOW("YELLOW");

    public static boolean MUTE = false;
    private Clip clip;

    private Sound (String fileName) {
        try {
            URL url = this.getClass().getClassLoader().getResource("res/" + fileName + ".wav");
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void preloadSounds () {
        values();
    }

    public void play () {
        if (MUTE || clip.isRunning())
            return;
        clip.setFramePosition(0);
        clip.start();
    }
}
