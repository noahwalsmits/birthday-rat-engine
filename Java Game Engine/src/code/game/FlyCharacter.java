package code.game;

import code.engine.visual.ScreenArea;
import code.engine.visual.ScreenSettings;
import code.engine.visual.drawable.DrawableImage;

public class FlyCharacter {
    private double xPosition;
    private double yPosition;
    private ScreenArea screenArea;
    private DrawableImage image;

    private static final double SPEED = 1000.0;

    public FlyCharacter(int startX, int startY) {
        this.xPosition = startX;
        this.yPosition = startY;
        this.screenArea = new ScreenArea(startX, startY, 100, 100);
        this.image = new DrawableImage("/resources/game/images/fly.png", this.screenArea, 5);
    }

    public void update(double time) {
        this.xPosition += (-SPEED * 0.5 + SPEED * Math.random()) * time;
        this.yPosition += (-SPEED * 0.5 + SPEED * Math.random()) * time;

        if (this.xPosition < 0.0) {
            this.xPosition = 0.0;
        }
        if (this.yPosition < 0.0) {
            this.yPosition = 0.0;
        }
        if (this.xPosition > ScreenSettings.baseWidth - this.screenArea.getBaseWidth()) {
            this.xPosition = ScreenSettings.baseWidth - this.screenArea.getBaseWidth();
        }
        if (this.yPosition > ScreenSettings.baseHeight - this.screenArea.getBaseHeight()) {
            this.yPosition = ScreenSettings.baseHeight - this.screenArea.getBaseHeight();
        }

        this.screenArea.moveTo((int) this.xPosition, (int) this.yPosition);
    }
}
