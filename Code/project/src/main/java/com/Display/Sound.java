package com.Display;

import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {


    static Clip clip;
    static Clip btnSound;
    static Clip winSound;
    static Clip heroSound;
    static  Clip lostSound;

    public void setMusic() {
        // Get the music in selected file path and make it an object, create reference to clip, and open the audio
        try {
            AudioInputStream musicstream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/Music_zapsplat.wav")));
            clip = AudioSystem.getClip();
            clip.open(musicstream);
        } catch(Exception ignored) {
        }
    }
    public void playClick(){
        try {
            AudioInputStream musicstream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/click.wav")));
            btnSound = AudioSystem.getClip();
            btnSound.open(musicstream);
        } catch(Exception ignored) {
        }
        btnSound.start();
    }
    public void lostSound(){
        try {
            AudioInputStream musicstream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/fail.wav")));
            lostSound = AudioSystem.getClip();
            lostSound.open(musicstream);
        } catch(Exception ignored) {
        }
        lostSound.start();
    }

    public void heroWalkSound(){
        try {
            heroSound = AudioSystem.getClip();
        } catch(Exception ignored) {}
        heroSound.start();
    }

    public void winSound(){
        try {
            winSound = AudioSystem.getClip();
        } catch(Exception ignored) {
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

