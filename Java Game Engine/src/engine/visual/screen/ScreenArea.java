package engine.visual.screen;

/**
 * Defines an area on a screen. The location is based on the base width and height of the screen, which are defined in ScreenSettings.
 * ScreenArea should resize to fit any screen size, though the aspect ratio is not currently maintained.
 */
public class ScreenArea {
    private int baseX;
    private int baseY;
    private int baseWidth;
    private int baseHeight;

    public ScreenArea(int baseX, int baseY, int baseWidth, int baseHeight) {
        this.baseX = baseX;
        this.baseY = baseY;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public int getX() {
        return this.baseX * (ScreenSettings.screenWidth / ScreenSettings.baseWidth);
    }

    public int getY() {
        return this.baseY * (ScreenSettings.screenHeight / ScreenSettings.baseHeight);
    }

    public int getWidth() {
        return this.baseWidth * (ScreenSettings.screenWidth / ScreenSettings.baseWidth);
    }

    public int getHeight() {
        return this.baseHeight * (ScreenSettings.screenHeight / ScreenSettings.baseHeight);
    }

    public void move(int xOffset, int yOffset) {
        this.baseX += xOffset;
        this.baseY += yOffset;
    }

    public void moveTo(int newBaseX, int newBaseY) {
        this.baseX = newBaseX;
        this.baseY = newBaseY;
    }

    public boolean isInsideScreen() {
        return this.getX() >= 0 &&
                this.getY() >= 0 &&
                this.getX() + this.getWidth() <= ScreenSettings.screenWidth &&
                this.getY() + this.getHeight() <= ScreenSettings.screenHeight;
    }

    @Override
    public String toString() {
        return "ScreenArea{(" + baseX + ", " + baseY + "), " + baseWidth + "x" + baseHeight + "}";
    }
}
