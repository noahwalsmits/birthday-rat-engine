package code.engine.visual;

import javafx.scene.canvas.GraphicsContext;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class RenderManager {
    private static RenderManager instance;
    private GraphicsContext graphics;
    private SortedSet<Drawable> drawables;
    private boolean isResizing;

    private static String TAG = "RenderManager";

    private RenderManager() {
        this.drawables = new TreeSet<>(this.drawableComparator());
    }

    static RenderManager getInstance() {
        if (instance == null) {
            instance = new RenderManager();
        }
        return instance;
    }

    /**
     * Should be performed before the RenderManager is used.
     *
     * @param graphics
     */
    void setGraphics(GraphicsContext graphics) {
        this.graphics = graphics;
    }

    void draw() {
        graphics.clearRect(0.0, 0.0, ScreenSettings.getScreenWidth(), ScreenSettings.getScreenHeight());
        for (Drawable drawable : this.drawables) {
            drawable.draw(this.graphics);
        }
    }

    /**
     * Adds a new Drawable to be rendered. The Drawable will not be added if a Drawable with the same hash code already exists in the RenderManager.
     * Or if the Drawable has no ScreenArea.
     *
     * @param drawable
     */
    void addDrawable(Drawable drawable) {
        if (drawable.getScreenArea() == null) {
            System.out.println(TAG + " warning: drawable had null ScreenArea and was not added");
            return;
        }
        this.drawables.add(drawable);
    }

    void removeDrawable(Drawable drawable) {
        this.drawables.remove(drawable);
    }

    /**
     * Removes all drawables from the RenderManager.
     */
    void clearDrawables() {
        this.drawables.clear();
    }

    void resizeDrawables() {
        //TODO prevent the creation of unnecessary threads
        Thread thread = new Thread(() -> {
            for (Drawable drawable : drawables) {
                drawable.resize();
            }
        });
        thread.start();
    }

    void stop() {
        //nothing to implement here yet
    }

    private Comparator<Drawable> drawableComparator() {
        return new Comparator<Drawable>() {
            @Override
            public int compare(Drawable o1, Drawable o2) {
                if (o1.hashCode() - o2.hashCode() != 0) {
                    int priority = o1.getPriority() - o2.getPriority();
                    if (priority == 0) {
                        return o1.hashCode() - o2.hashCode(); //If the same priority
                    }
                    return priority; //If different priority
                }
                return 0; //If the same hash code
            }
        };
    }
}
