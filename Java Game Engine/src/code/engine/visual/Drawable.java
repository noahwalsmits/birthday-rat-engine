package code.engine.visual;

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

    public void show() {
        RenderManager.getInstance().addDrawable(this);
        this.resize();
    }

    public void hide() {
        RenderManager.getInstance().removeDrawable(this);
    }

    public abstract void resize();

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
