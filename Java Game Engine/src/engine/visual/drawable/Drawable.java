package engine.visual.drawable;

import engine.visual.RenderManager;
import engine.visual.screen.ScreenArea;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Drawable {
    private ScreenArea screenArea;
    private Image image;
    private int priority;
    private OnDrawableUpdate onDrawableUpdate;

    /**
     * Creates a drawable, keep in mind that it will still need to be added to the render manager for it to be drawn
     *
     * @param screenArea The area on the screen the drawable should take up
     * @param imagePath  The path to the image that should be drawn, should lead to an image file in the resources folder
     * @param priority   The priority compared to other drawables, higher priority drawables get drawn on top of lower priority ones
     */
    public Drawable(String imagePath, ScreenArea screenArea, int priority) {
        try {
            this.image = new Image(getClass().getResource(imagePath).toString(),
                    (double) screenArea.getWidth(),
                    (double) screenArea.getHeight(),
                    false,
                    true); //true = better quality resize || false = faster resize
        } catch (NullPointerException e) {
            System.out.println("Drawable: Could not find image");
            this.image = new Image(getClass().getResource("/images/engine/missing_image.png").toString(),
                    (double) screenArea.getWidth(),
                    (double) screenArea.getHeight(),
                    false,
                    true); //true = better quality resize || false = faster resize
            e.printStackTrace();
        }
        this.screenArea = screenArea;
        this.priority = priority;
    }

    /**
     * Drawn the drawable on the screen
     * @param graphics A graphics context to draw on
     */
    public void draw(GraphicsContext graphics) {
        graphics.drawImage(this.image, this.screenArea.getX(), this.screenArea.getY());
    }

    /**
     * Executes the code given to onDrawableUpdate
     *
     * @param time The amount of milliseconds gone by since the last update, not used by default
     */
    public void update(double time) {
        if (this.onDrawableUpdate != null) {
            this.onDrawableUpdate.onUpdate(this.screenArea, time);
        }
    }

    /**
     * Makes the drawable remove itself from the render manager
     */
    public void remove() {
        RenderManager.getInstance().removeDrawable(this);
    }

    public ScreenArea getScreenArea() {
        return screenArea;
    }

    public int getPriority() {
        return priority;
    }

    public void setOnDrawableUpdate(OnDrawableUpdate onDrawableUpdate) {
        this.onDrawableUpdate = onDrawableUpdate;
    }

    /**
     * Interface definition for a callback to be invoked when the drawable is updated
     */
    public interface OnDrawableUpdate {
        void onUpdate(ScreenArea screenArea, double time);
    }

    @Override
    public String toString() {
        return "Drawable{" +
                "screenArea=" + screenArea +
                ", image=" + image +
                ", onDrawableUpdate=" + (onDrawableUpdate != null) +
                ", priority=" + priority +
                '}';
    }
}
