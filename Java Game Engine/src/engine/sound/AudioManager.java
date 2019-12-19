package engine.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
    private static AudioManager instance;
    private MediaPlayer musicPlayer;


    private AudioManager() {
        System.out.println(getClass().getResource("Intermission.mp3"));
        //this.musicPlayer = new MediaPlayer(new Media(getClass().getResource("Intermission.mp3").toExternalForm()));
        //this.musicPlayer.play();
        //TODO play startup sound to create musicPlayer instance, that way it's no longer needed to check if the player is not null
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            synchronized (AudioManager.class) {
                if (instance == null) {
                    instance = new AudioManager();
                }
            }
        }
        return instance;
        //TODO maybe this doesn't need to be syncrhonized
    }

    public void playSong(String mediaSource, boolean displayName) {
        if (this.musicPlayer != null) {
            this.musicPlayer.dispose();
        }
        this.musicPlayer = new MediaPlayer(new Media(mediaSource));
        this.musicPlayer.play();

        if (displayName) {
            //TODO pop-up on screen with track name
        }
    }

    public void stopSong() {
        if (this.musicPlayer != null) {
            this.musicPlayer.stop();
            this.musicPlayer.dispose();
        }
    }

    public void playSound(String mediaSource, double volume, double rate, double balance, int priority) {
        AudioClip clip = new AudioClip(mediaSource);
        clip.setVolume(volume); //default = 1.0
        clip.setRate(rate); //default = 1.0
        clip.setBalance(balance); //default = 0.0
        clip.setPriority(priority); //default = 0
        clip.play();
    }

    public void playSound(String mediaSource) {
        this.playSound(mediaSource, 1.0, 1.0, 0.0, 0);
    }


}
