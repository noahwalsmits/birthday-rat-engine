package code.game;

import code.engine.visual.ScreenArea;
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
        this.screenArea.moveTo((int) this.xPosition, (int) this.yPosition);
    }
}
