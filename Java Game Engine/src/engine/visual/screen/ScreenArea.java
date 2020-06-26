package engine.visual.screen;

/**
 * Defines an area on a screen. The location is based on the game width and height of the screen, which are defined in ScreenSettings.
 * ScreenArea should resize to fit any screen size, though the aspect ratio is not currently maintained.
 * A screen area allows a developer to only keep track of the game screen size when placing things like drawables.
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

    /**
     * Calculates where the game X would be if it was resized to fit the current screen size
     *
     * @return The X coordinate resized to fit the current screen size
     */
    public double getX() {
        return this.baseX * ((double) ScreenSettings.screenWidth / ScreenSettings.baseWidth);
    }

    /**
     * Calculates where the game Y would be if it was resized to fit the current screen size
     *
     * @return The Y coordinate resized to fit the current screen size
     */
    public double getY() {
        return this.baseY * ((double) ScreenSettings.screenHeight / ScreenSettings.baseHeight);
    }

    /**
     * TODO documentation
     *
     * @return The width resized to fit the current screen size
     */
    public double getWidth() {
        return this.baseWidth * ((double) ScreenSettings.screenWidth / ScreenSettings.baseWidth);
    }

    /**
     * @return The height resized to fit the current screen size
     */
    public double getHeight() {
        return this.baseHeight * ((double) ScreenSettings.screenHeight / ScreenSettings.baseHeight);
    }

    /**
     * Moves the game coordinates of the screen area using an offset
     *
     * @param xOffset The X offset compared to the game screen size
     * @param yOffset The Y offset compared to the game screen size
     */
    public void move(int xOffset, int yOffset) {
        this.baseX += xOffset;
        this.baseY += yOffset;
    }

    /**
     * Moves the game coordinates of the screen area by replacing them
     *
     * @param newBaseX The new X coordinate compared to the game screen size
     * @param newBaseY The new Y coordinate compared to the game screen size
     */
    public void moveTo(int newBaseX, int newBaseY) {
        this.baseX = newBaseX;
        this.baseY = newBaseY;
    }

    /**
     * Checks if the screen area is within the screen
     *
     * @return True if it is inside the screen, false if it is not
     */
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

    /**
     * Creates a screen area that is positioned in the middle of the screen, the middle of the screen area will correspond with the middle of the screen
     *
     * @param baseWidth  The game width of the screen area
     * @param baseHeight The game height of the screen area
     * @return A screen area that is positioned in the middle of the screen
     */
    public static ScreenArea MIDDLE(int baseWidth, int baseHeight) {
        int x = (ScreenSettings.screenWidth / 2) + (baseWidth / 2);
        int y = (ScreenSettings.screenHeight / 2) + (baseHeight / 2);
        return new ScreenArea(x, y, baseWidth, baseHeight); //TODO test
    }

    public static ScreenArea HIDDEN() {
        return new ScreenArea(-1, -1, 0, 0);
    }
}
