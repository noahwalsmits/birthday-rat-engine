package engine.visual.drawable;

import engine.visual.screen.ScreenArea;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URI;

public class Drawable {
    private ScreenArea screenArea;
    private Image image;
    private OnDrawableUpdate onDrawableUpdate;
    private int priority;

    public Drawable(ScreenArea screenArea, String imageUrl) {
        this.screenArea = screenArea;
        this.image = new Image(getClass().getResource(imageUrl).toString(),
                (double) screenArea.getWidth(),
                (double) screenArea.getHeight(),
                false,
                true);
        //smooth = true, better quality resize
        //smooth = false, faster resize
    }

    public void draw(GraphicsContext graphics) {
        //TODO resize
        graphics.drawImage(this.image, this.screenArea.getX(), this.screenArea.getY());
    }

    public void update(long time) {
        if (this.onDrawableUpdate != null) {
            this.onDrawableUpdate.onUpdate(this.screenArea, time);
        }
    }

    public ScreenArea getScreenArea() {
        return screenArea;
    }

    public int getPriority() {
        return priority;
    }

    public void setOnDrawableUpdate(OnDrawableUpdate onDrawableUpdate) {

    }

    public interface OnDrawableUpdate {
        void onUpdate(ScreenArea screenArea, long time);
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
