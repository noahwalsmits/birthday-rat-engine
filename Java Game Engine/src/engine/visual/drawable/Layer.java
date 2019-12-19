package engine.visual.drawable;

import engine.visual.animation.ConfigClass;
import engine.visual.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.jfree.fx.FXGraphics2D;

/**
 * A group of drawables to be drawn on the same layer. They are not supposed to overlap eachother.
 * Minimizing the amount of layers used should increase performance when drawing.
 */
public class Layer {
    private List<Drawable> drawables;
    private String name;
    private byte priority;
    private boolean enabled;
    private ExecutorService threads;

    /**
     * Creates a Layer object.
     * @param name The name that can be used to find this layer, used to enable/disable this layer. The default name is "".
     * @param priority Determines when this layer gets drawn in relation to other layers. The lowest number gets drawn the earliest.
     */
    public Layer(String name, byte priority) {
        this.drawables = new ArrayList<>();
        this.name = name;
        this.priority = priority;
        this.enabled = true;
        this.threads = Executors.newCachedThreadPool();
    }

    public Layer(String name) {
        this(name, ConfigClass.DEFAULT_LAYER_PRIORITY);
    }

    public Layer(byte priority) {
        this(null, priority);
    }

    public Layer() {
        this(null, ConfigClass.DEFAULT_LAYER_PRIORITY);
    }

    public void draw(FXGraphics2D graphics) {
        for (Drawable drawable: this.drawables) {
            //drawable.draw(graphics); //
            this.threads.execute(() -> drawable.draw(graphics));
        }
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public String getName() {
        if (this.name != null) {
            return name;
        }
        return "";
    }

    public byte getPriority() {
        return priority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
