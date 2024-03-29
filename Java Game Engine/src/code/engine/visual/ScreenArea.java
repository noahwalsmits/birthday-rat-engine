package code.engine.visual;

/**
 * Defines an area on a screen. The location is based on the code.game width and height of the screen, which are defined in ScreenSettings.
 * ScreenArea should resize to fit any screen size, though the aspect ratio is not currently maintained.
 * A screen area allows a developer to only keep track of the code.game screen size when placing things like drawables.
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
     * Calculates where the code.game X would be if it was resized to fit the current screen size
     *
     * @return The X coordinate resized to fit the current screen size
     */
    public double getX() {
        return this.baseX * ((double) ScreenSettings.getScreenWidth() / ScreenSettings.baseWidth);
    }

    /**
     * Calculates where the code.game Y would be if it was resized to fit the current screen size
     *
     * @return The Y coordinate resized to fit the current screen size
     */
    public double getY() {
        return this.baseY * ((double) ScreenSettings.getScreenHeight() / ScreenSettings.baseHeight);
    }

    /**
     * @return The width resized to fit the current screen size
     */
    public double getWidth() {
        return this.baseWidth * ((double) ScreenSettings.getScreenWidth() / ScreenSettings.baseWidth);
    }

    /**
     * @return The height resized to fit the current screen size
     */
    public double getHeight() {
        return this.baseHeight * ((double) ScreenSettings.getScreenHeight() / ScreenSettings.baseHeight);
    }

    /**
     * Moves the code.game coordinates of the screen area using an offset
     *
     * @param xOffset The X offset compared to the code.game screen size
     * @param yOffset The Y offset compared to the code.game screen size
     */
    public void move(int xOffset, int yOffset) {
        this.baseX += xOffset;
        this.baseY += yOffset;
    }

    /**
     * Moves the code.game coordinates of the screen area by replacing them
     *
     * @param newBaseX The new X coordinate compared to the code.game screen size
     * @param newBaseY The new Y coordinate compared to the code.game screen size
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
                this.getX() + this.getWidth() <= ScreenSettings.getScreenWidth() &&
                this.getY() + this.getHeight() <= ScreenSettings.getScreenHeight();
    }

    /**
     * Checks if this screen area intersects with another
     *
     * @param other          The other screen area
     * @param boundaryOffset Offsets how close the screen areas need to be in order to intersect eachother
     * @return True if the screen areas overlap after applying the boundary offset
     */
    public boolean intersectsWith(ScreenArea other, int boundaryOffset) {
        return this.baseX < other.baseX + other.baseWidth + boundaryOffset
                && this.baseX + this.baseWidth + boundaryOffset > other.baseX
                && this.baseY < other.baseY + other.baseHeight + boundaryOffset
                && this.baseY + this.baseHeight + boundaryOffset > other.baseY;
    }

    public boolean intersectsWith(ScreenArea other) {
        return this.intersectsWith(other, 0);
    }

    /**
     * @param other The other screen area
     * @return The distance between the centers of the two screen areas
     */
    public double distanceBetween(ScreenArea other) {
        return Math.hypot(
                (this.baseX + this.baseWidth * 0.5) - (other.baseX + other.baseWidth * 0.5),
                (this.baseY + this.baseHeight * 0.5) - (other.baseY + other.baseHeight * 0.5)
        );
    }

    public int getBaseX() {
        return baseX;
    }

    public int getBaseY() {
        return baseY;
    }

    public int getBaseWidth() {
        return baseWidth;
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    @Override
    public String toString() {
        return "ScreenArea{(" + baseX + ", " + baseY + "), " + baseWidth + "x" + baseHeight + "}";
    }

    /**
     * Creates a screen area that is positioned in the middle of the screen, the middle of the screen area will correspond with the middle of the screen
     *
     * @param baseWidth  The code.game width of the screen area
     * @param baseHeight The code.game height of the screen area
     * @return A screen area that is positioned in the middle of the screen
     */
    public static ScreenArea MIDDLE(int baseWidth, int baseHeight) {
        int x = (ScreenSettings.getScreenWidth() / 2) + (baseWidth / 2);
        int y = (ScreenSettings.getScreenHeight() / 2) + (baseHeight / 2);
        return new ScreenArea(x, y, baseWidth, baseHeight);
    }

    public static ScreenArea HIDDEN() {
        return new ScreenArea(-1, -1, 0, 0);
    }
}
