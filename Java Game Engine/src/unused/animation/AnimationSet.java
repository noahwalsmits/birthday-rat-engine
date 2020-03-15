package unused.animation;

import java.awt.image.BufferedImage;

public class AnimationSet {
    private String name;
    private BufferedImage[] frames;
    private int currentFrame;
    private float frameTime;
    private boolean playing;
    private boolean reversed;

    public AnimationSet(String name, BufferedImage[] frames) {
        this.name = name;
        this.frames = frames;
        this.currentFrame = 0;
        this.frameTime = ConfigClass.DEFAULT_FRAME_TIME;
        this.playing = true;
        this.reversed = false;
    }

    public void update() {
        if (this.playing) {
            if (this.reversed) {
                this.previousFrame();
            } else {
                this.nextFrame();
            }
        }
    }

    public void draw() {

    }

    public void nextFrame() {
        this.changeFrame(1);
    }

    public void previousFrame() {
        this.changeFrame(-1);
    }

    public void play() {
        this.playing = true;
    }

    public void pause() {
        this.playing = false;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public void setFrameTime(float frameTime) {
        this.frameTime = frameTime;
    }

    private void changeFrame(int change) {
        this.currentFrame += change;
        if (this.currentFrame > this.frames.length) {
            this.currentFrame = 0;
        } else if (this.currentFrame < 0) {
            this.currentFrame = this.frames.length;
        }
    }

    public String getName() {
        return name;
    }
}
