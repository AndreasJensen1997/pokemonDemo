package com.example.pokemondemo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.AudioClip;
import java.net.URL;

public class AudioManager {
    private MediaPlayer mediaPlayer;
    private AudioClip buttonClip;

    public AudioManager() {
        loadButtonSound();
    }

    public void startBackgroundMusic() {
        URL resource = getClass().getResource("/music/littleroot_town.mp3");
        if (resource != null) {
            Media sound = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        } else {
            System.out.println("Background music not found!");
        }
    }

    private void loadButtonSound() {
        URL resource = getClass().getResource("/music/button.mp3");
        if (resource != null) {
            buttonClip = new AudioClip(resource.toString());
            buttonClip.setVolume(0.5);
        } else {
            System.out.println("Button sound not found!");
        }
    }

    public void playButtonSound() {
        if (buttonClip != null) {
            buttonClip.play();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}