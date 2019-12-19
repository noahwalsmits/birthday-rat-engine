package engine.visual.drawable;

import engine.visual.animation.ConfigClass;
import engine.visual.drawable.Layer;
import org.jfree.fx.FXGraphics2D;

/**
 * @deprecated
 * A layer that can be searched for by it's name, so that it can be found and disabled/enabled;
 */
public class TrackedLayer extends Layer {
    private String name;
    private boolean enabled;

    public TrackedLayer(String name, byte priority) {
        super(priority);
        this.name = name;
    }

    public TrackedLayer(String name) {
        this(name, ConfigClass.DEFAULT_LAYER_PRIORITY);
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        if (this.enabled) {
            super.draw(graphics);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
