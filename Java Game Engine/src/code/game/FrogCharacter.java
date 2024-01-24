package code.game;

import code.engine.visual.ScreenArea;
import code.engine.visual.ScreenSettings;
import code.engine.visual.drawable.DrawableImage;

public class FrogCharacter {
    private double horizontalInput;
    private double verticalInput;
    private double xPosition;
    private double yPosition;
    private ScreenArea screenArea;
    private DrawableImage image;

    private static final double SPEED = 500.0;

    public FrogCharacter(int startX, int startY) {
        this.horizontalInput = 0.0;
        this.verticalInput = 0.0;
        this.xPosition = startX;
        this.yPosition = startY;
        this.screenArea = new ScreenArea(startX, startY, 150, 150);
        this.image = new DrawableImage("/resources/game/images/frog2.png", this.screenArea, 10);
    }

    public void update(double time) {
        this.xPosition += this.horizontalInput * SPEED * time;
        this.yPosition -= this.verticalInput * SPEED * time;

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

    public ScreenArea getScreenArea() {
        return screenArea;
    }
}
