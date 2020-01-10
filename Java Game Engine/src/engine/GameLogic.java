package engine;

public abstract class GameLogic {

    /**
     * This method will be run at startup
     */
    public abstract void init();

    /**
     * This method will be run every update
     * @param time The amount of seconds since the last update
     */
    public abstract void update(double time);

    /**
     * This method will be run when the application closes
     */
    public abstract void stop();
}
