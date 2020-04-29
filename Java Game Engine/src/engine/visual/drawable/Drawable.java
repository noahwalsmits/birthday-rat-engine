package engine.visual.drawable;

import engine.visual.RenderManager;
import engine.visual.screen.ScreenArea;
import javafx.scene.canvas.GraphicsContext;

abstract public class Drawable {
    private ScreenArea screenArea;
    private int priority;

    public Drawable(ScreenArea screenArea, int priority) {
        this.screenArea = screenArea;
        this.priority = priority;
    }

    /**
     * Draws the drawable on the screen. The RenderManager executes this every frame.
     *
     * @param graphics A graphics context to draw on, is provided by RenderManager
     */
    abstract public void draw(GraphicsContext graphics);

    public void remove() {
        RenderManager.getInstance().removeDrawable(this);
    }

    public ScreenArea getScreenArea() {
        return this.screenArea;
    }

    public void setScreenArea(ScreenArea screenArea) {
        this.screenArea = screenArea;
    }

    public int getPriority() {
        return this.priority;
    }
}
