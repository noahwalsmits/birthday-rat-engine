package engine.visual.drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of drawables that should not be moved independently from one another.
 */
public class DrawGroup {
    private List<Drawable> drawables;

    public DrawGroup(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    public DrawGroup() {
        this(new ArrayList<>());
    }

    public void move(int xOffset, int yOffset) {
        for (Drawable drawable : this.drawables) {
            drawable.getScreenArea().move(xOffset, yOffset);
        }
    }

    public void moveTo(int newBaseX, int newBaseY) {
        for (Drawable drawable : this.drawables) {
            drawable.getScreenArea().moveTo(newBaseX, newBaseY);
        }
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }
}
