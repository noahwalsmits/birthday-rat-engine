package code.game;

import code.engine.visual.ScreenArea;
import code.engine.visual.drawable.DrawableImage;

public class FrogCharacter {
    private double horizontalInput;
    private double verticalInput;
    private double xPosition;
    private double yPosition;
    private ScreenArea screenArea;
    private DrawableImage image;

    private static final double SPEED = 50.0;

    public FrogCharacter() {
        this.horizontalInput = 0.0;
        this.verticalInput = 0.0;
        this.screenArea = new ScreenArea(100, 100, 100, 100);
        this.image = new DrawableImage("/resources/game/images/frog.png", this.screenArea, 10);
    }

    public void update(double time) {
        this.xPosition += this.horizontalInput * SPEED * time;
        this.yPosition -= this.verticalInput * SPEED * time;
        this.screenArea.moveTo((int) this.xPosition, (int) this.yPosition);
    }

    public double getHorizontalInput() {
        return horizontalInput;
    }

    public void setHorizontalInput(double horizontalInput) {
        this.horizontalInput = horizontalInput;
    }

    public double getVerticalInput() {
        return verticalInput;
    }

    public void setVerticalInput(double verticalInput) {
        this.verticalInput = verticalInput;
    }
}
