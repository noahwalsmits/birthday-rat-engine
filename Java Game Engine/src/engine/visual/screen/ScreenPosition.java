package engine.visual.screen;

/**
 * @deprecated
 */
public class ScreenPosition {
    private int baseX;
    private int baseY;

    public ScreenPosition(int baseX, int baseY) {
        this.baseX = baseX;
        this.baseY = baseY;
    }

    public int getX() {
        return this.baseX * (ScreenSettings.screenWidth / ScreenSettings.baseWidth);
    }

    public int getY() {
        return this.baseY * (ScreenSettings.screenHeight / ScreenSettings.baseHeight);
    }

}
