package engine.visual;

import engine.visual.drawable.Drawable;
import engine.visual.screen.ScreenSettings;
import javafx.scene.canvas.GraphicsContext;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TODO stop using singleton
public class RenderManager {
    private static RenderManager instance;
    private GraphicsContext graphics;
    private SortedSet<Drawable> drawables;
    private ExecutorService updateExecuter; //shut down eventually
    //Set:
    //no duplicates

    //(SortedSet) Treeset:
    //https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html
    //No duplicates
    //Sorting
    //This implementation provides guaranteed log(n) time cost for the basic operations (add, remove and contains).

    //List:
    //Sorting

    private RenderManager() {
        this.drawables = new TreeSet<>(this.drawableComparator());
        this.updateExecuter = Executors.newCachedThreadPool();
    }

    public static RenderManager getInstance() {
//        if (instance == null) {
//            synchronized (RenderManager.class) {
//                if (instance == null) {
//                    instance = new RenderManager();
//                }
//            }
//        }
//        return instance;
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
        graphics.clearRect(0.0, 0.0, ScreenSettings.screenWidth, ScreenSettings.screenHeight);
        for (Drawable drawable : this.drawables) {
            drawable.draw(this.graphics);
        }
    }

    /**
     * Updates every drawable using a threadpool
     *
     * @param time
     */
    void asyncUpdate(double time) {
        for (Drawable drawable : this.drawables) {
            this.updateExecuter.execute(new Runnable() {
                @Override
                public void run() {
                    drawable.update(time);
                }
            });
        }
    }

    /**
     * Adds a new Drawable to be rendered. The Drawable will not be added if a Drawable with the same hash code already exists in the RenderManager.
     *
     * @param drawable
     */
    public void addDrawable(Drawable drawable) {
        this.drawables.add(drawable);
    }

    public void removeDrawable(Drawable drawable) {
        this.drawables.remove(drawable);
    }

    public void clearDrawables() {
        this.drawables.clear();
    }

    private Comparator<Drawable> drawableComparator() {
        return new Comparator<Drawable>() { //TODO
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

    void stop() {
        this.updateExecuter.shutdownNow();
    }

    @Override
    public String toString() {
        return "RenderManager{" +
                "drawables=" + drawables +
                '}';
    }
}
