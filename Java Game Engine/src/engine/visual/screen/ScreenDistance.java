package engine.visual.screen;

/**
 * @deprecated
 */
public class ScreenDistance {
    private int baseDistance;
    private boolean directionX;

    public ScreenDistance(int baseDistance, char direction) {
        this.baseDistance = baseDistance;

        if (direction == 'x') {
            this.directionX = true;
        } else if (direction == 'y') {
            this.directionX = false;
        } else {
            System.out.println("DevErr: ScreenDistance direction must be 'x' or 'y'");
        }
    }

    public int getDistance() {
        if (this.directionX) {
            return this.baseDistance * (ScreenSettings.screenWidth / ScreenSettings.baseWidth);
        } else {
            return this.baseDistance * (ScreenSettings.screenHeight / ScreenSettings.baseHeight);
        }
    }

}
