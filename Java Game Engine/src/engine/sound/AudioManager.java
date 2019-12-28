package engine.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
    private static AudioManager instance;
    private MediaPlayer musicPlayer;

    private AudioManager() {

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
        //TODO maybe this doesn't need to be synchronized
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

    /**
     * Plays a sound file, multiple can be played at once when this method is called multiple times
     *
     * @param mediaSource A path that leads to an audio file in the resources folder
     * @param volume      The volume, 1.0 is full volume
     * @param rate        The speed at which the sound is played, can range from 1/8 to 8x speed, 1.0 is normal speed
     * @param balance     The balance between the left and right volume, -1.0 is full volume for left, 1.0 is full volume for right, 0.0 is full volume for both
     * @param priority    The priority that decides which clips still get played when there are too many, the default is 0
     */
    public void playSound(String mediaSource, double volume, double rate, double balance, int priority) {
        AudioClip clip = new AudioClip(mediaSource);
        clip.setVolume(volume); //default = 1.0
        clip.setRate(rate); //default = 1.0
        clip.setBalance(balance); //default = 0.0
        clip.setPriority(priority); //default = 0
        clip.play();
    }

    /**
     * Plays a sound file using the default options, multiple can be played at once when this method is called multiple times
     *
     * @param mediaSource A path that leads to an audio file in the resources folder
     */
    public void playSound(String mediaSource) {
        this.playSound(mediaSource, 1.0, 1.0, 0.0, 0);
    }

    //Plays a sound from a sound pool TODO
    public void playSoundPool(String soundPoolFolder, double volume, double rate, double balane, int priority) {

    }


}
