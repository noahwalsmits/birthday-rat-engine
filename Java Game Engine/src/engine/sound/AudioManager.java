package engine.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
    private static AudioManager instance;
    private MediaPlayer musicPlayer;
    private double musicVolume;
    private double musicRate;
    private double musicBalance;

    private AudioManager() {
        this.musicVolume = 1.0;
        this.musicRate = 1.0;
        this.musicBalance = 0.0;
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

    /**
     * Plays a song, only a single song can be played at a time
     *
     * @param mediaSource The path to the song that should be played, should lead to an audio file in the resources folder
     */
    public void playMusic(String mediaSource, boolean loop) {
        try {
            mediaSource = getClass().getResource(mediaSource).toString();
        } catch (NullPointerException e) {
            System.out.println("AudioManager: Could not find song");
            e.printStackTrace();
            return;
        }

        if (this.musicPlayer != null) {
            this.musicPlayer.dispose();
        }
        this.musicPlayer = new MediaPlayer(new Media(mediaSource));
        if (loop) {
            this.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        this.musicPlayer.setVolume(this.musicVolume);
        this.musicPlayer.setRate(this.musicRate);
        this.musicPlayer.setBalance(this.musicBalance);
        this.musicPlayer.play();
    }

    public void playMusicPool(String musicPoolId, boolean loop) {
        //TODO parse the json and loop random songs from it
    }

    /**
     * Stops the current song from playing, if a song is playing
     */
    public void stopMusic() {
        if (this.musicPlayer != null) {
            this.musicPlayer.stop();
            this.musicPlayer.dispose();
        }
    }

    /**
     * Changes the volume of the music
     *
     * @param volume The volume, 1.0 is full volume and the default
     */
    public void setMusicVolume(double volume) {
        if (this.musicPlayer != null) {
            this.musicPlayer.setVolume(volume);
        }
        this.musicVolume = volume;
    }

    /**
     * Changes the speed of the music
     *
     * @param rate The speed, 1.0 is default, can range from 0.0 to 8.0
     */
    public void setMusicRate(double rate) {
        if (this.musicPlayer != null) {
            this.musicPlayer.setRate(rate);
        }
        this.musicRate = rate;
    }

    /**
     * Changes the balance between the left and right volume
     *
     * @param balance The balance between the left and right volume, -1.0 is full volume for left, 1.0 is full volume for right, 0.0 is full volume for both
     */
    public void setMusicBalance(double balance) {
        if (this.musicPlayer != null) {
            this.musicPlayer.setBalance(balance);
        }
        this.musicBalance = balance;
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
        try {
            mediaSource = getClass().getResource(mediaSource).toString();
        } catch (NullPointerException e) {
            System.out.println("AudioManager: Could not find sound");
            e.printStackTrace();
            return;
        }

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
