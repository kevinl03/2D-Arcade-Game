package Display;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {


    static Clip clip;
    static Clip btnSound;
    static Clip winSound;
    static Clip heroSound;
    static  Clip lostSound;

    URL Musicpath[] = new URL[40];

    public void setMusic() {
        // Get the music in selected file path and make it an object, create reference to clip, and open the audio
        try {
            AudioInputStream musicstream = AudioSystem.getAudioInputStream(getClass().getResource("/Music_zapsplat.wav"));
            clip = AudioSystem.getClip();
            clip.open(musicstream);
        } catch(Exception e) {
            System.out.println("Failed\n");
        }
    }
    public void playClick(){
        try {
            AudioInputStream musicstream = AudioSystem.getAudioInputStream(getClass().getResource("/click.wav"));
            btnSound = AudioSystem.getClip();
            btnSound.open(musicstream);
        } catch(Exception e) {
            System.out.println("Failed\n");
        }
        btnSound.start();
    }
    public void lostSound(){
        try {
            AudioInputStream musicstream = AudioSystem.getAudioInputStream(getClass().getResource("/fail.wav"));
            lostSound = AudioSystem.getClip();
            lostSound.open(musicstream);
        } catch(Exception e) {
            System.out.println("Failed\n");
        }
        lostSound.start();
    }

    public void heroWalkSound(){
        try {
            //AudioInputStream musicstream = AudioSystem.getAudioInputStream(getClass().getResource("/walk.mp3"));
            heroSound = AudioSystem.getClip();
            //heroSound.open(musicstream);
        } catch(Exception e) {
            System.out.println("Failed\n");
        }
        heroSound.start();
    }

    public void winSound(){
        try {
            //AudioInputStream musicstream = AudioSystem.getAudioInputStream(getClass().getResource("/win.mp3"));
            winSound = AudioSystem.getClip();
            //winSound.open(musicstream);
        } catch(Exception e) {
            System.out.println("Failed\n");
        }
        winSound.start();
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

    public void playMusic(int firstTime) {
        if(firstTime==1) {
            setMusic();
            play();
            loop();
        }
        else {
            stopMusic();
            setMusic();
            play();
            loop();
        }
    }

    public void startupMusic() {
        playMusic(1);
    }

}

