package engine.visual;

/**
 * Used to resize to other screen sizes
 * Different screen ratio's will cause it to look stretched
 */
public class ScreenSettings { //TODO doubles or int?
    public static final int baseWidth = 1920;
    public static final int baseHeight = 1080; //todo find a better solution for this
    private static int screenWidth = 1440;
    private static int screenHeight = 810;

    public static int getScreenWidth() {
        return ScreenSettings.screenWidth;
    }

    public static int getScreenHeight() {
        return ScreenSettings.screenHeight;
    }

    public static void setScreenWidth(int screenWidth) {
        ScreenSettings.screenWidth = screenWidth;
        RenderManager.getInstance().resizeDrawables();
    }

    public static void setScreenHeight(int screenHeight) {
        ScreenSettings.screenHeight = screenHeight;
        RenderManager.getInstance().resizeDrawables();
    }

    public static int toBaseX(double screenX) {//
        return (int) (screenX / (ScreenSettings.screenWidth / ScreenSettings.baseWidth));
    }

    public static int toBaseY(double screenY) {//
        return (int) (screenY / (ScreenSettings.screenHeight / ScreenSettings.baseHeight));
    }
}
