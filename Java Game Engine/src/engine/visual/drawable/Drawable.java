package engine.visual.drawable;

import engine.visual.screen.ScreenArea;
import org.jfree.fx.FXGraphics2D;

import java.awt.image.BufferedImage;

public class Drawable {
    private ScreenArea screenArea;
    private BufferedImage image;
    private OnDrawableUpdate onDrawableUpdate;

    public Drawable(ScreenArea screenArea, BufferedImage image) {
        this.screenArea = screenArea;
        this.image = image;
        //this.image = image.getScaledInstance(this.screenArea.getWidth(), this.screenArea.getHeight(), Image.SCALE_DEFAULT);
        //new BufferedImage(this.screenArea.getWidth(), this.screenArea.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void draw(FXGraphics2D graphics) {
        graphics.drawImage(this.image,
                0,
                this.image.getHeight(),
                this.screenArea.getWidth(), //this.image.getWidth() * (this.screenArea.getWidth() / this.image.getWidth())
                this.screenArea.getHeight(), //this.image.getHeight() * (this.screenArea.getHeight() / this.image.getHeight())
                null);
    }

    public void update(long time) {
        if (this.onDrawableUpdate != null) {
            this.onDrawableUpdate.onUpdate(this.screenArea, time);
        }
    }

    public ScreenArea getScreenArea() {
        return screenArea;
    }

    public void setOnDrawableUpdate(OnDrawableUpdate onDrawableUpdate) {

    }

    public interface OnDrawableUpdate {
        void onUpdate(ScreenArea screenArea, long time);
    }
}
