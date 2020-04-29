package engine.visual.screen;

/**
 * Used to resize to other screen sizes
 * Different screen ratio's will cause it to look stretched
 */
public class ScreenSettings { //TODO doubles or int?
    public static final int baseWidth = 1920;
    public static final int baseHeight = 1080; //todo find a better solution for this
    public static int screenWidth = 700;
    public static int screenHeight = 500;
    public static boolean firstStartup = true;

    public static int toBaseX(double screenX) {//
        return (int) (screenX / (ScreenSettings.screenWidth / ScreenSettings.baseWidth));
    }

    public static int toBaseY(double screenY) {//
        return (int) (screenY / (ScreenSettings.screenHeight / ScreenSettings.baseHeight));
    }
}
