package engine.visual;

import engine.visual.drawable.Layer;
import engine.visual.screen.ScreenSettings;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderManager {
    private static RenderManager instance;
    private FXGraphics2D graphics;
    private List<Layer> layers;

    private RenderManager() {
        this.layers = new ArrayList<>();
    }

    public static RenderManager getInstance() {
        if (instance == null) {
            synchronized (RenderManager.class) {
                if (instance == null) {
                    instance = new RenderManager();
                }
            }
        }
        return instance;
    }

    /**
     * Should be performed before the RenderManager is used.
     * @param graphics
     */
    protected void setGraphics(FXGraphics2D graphics) {
        this.graphics = graphics;
    }

    private void generateDefaultLayers() {
        this.layers.add(new Layer("interfacefront", (byte) 5));
        this.layers.add(new Layer("interfaceback", (byte) 4));
        this.layers.add(new Layer("foreground", (byte) 5));
        this.layers.add(new Layer("middle", (byte) 6));
        this.layers.add(new Layer("background", (byte) 7));
    }

    public void draw() {
        graphics.setBackground(Color.BLACK);
        graphics.clearRect(0, 0, ScreenSettings.screenWidth, ScreenSettings.screenHeight);
        for (Layer layer : this.layers) {
            layer.draw(this.graphics);
        }
    }

    public List<Layer> getLayers() {
        return layers;
    }
}
