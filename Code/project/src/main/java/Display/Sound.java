package Display;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {


    static Clip clip;
    URL Musicpath[] = new URL[40];

    public void Music() 	{

        Musicpath[0] = getClass().getResource("/resources/Music_zapsplat.wav");
    }

    public void setMusic(int choice) {
        // Get the music in selected file path and make it an object, create reference to clip, and open the audio
        try {
            AudioInputStream musicstream = AudioSystem.getAudioInputStream(Musicpath[choice]);
            clip = AudioSystem.getClip();
            clip.open(musicstream);
        } catch(Exception e) {
            System.out.println("Failed\n");
        }
    }

    public void play() {
        clip.start();
    }

    public static void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic() {
        clip.stop();
    }

    public void playMusic(int choice) {
        setMusic(choice);
        play();
        loop();
    }

    public void startupMusic() {
        playMusic(0);
    }
}

