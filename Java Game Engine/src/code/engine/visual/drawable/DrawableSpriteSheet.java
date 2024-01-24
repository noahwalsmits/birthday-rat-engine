package code.engine.visual.drawable;

import code.engine.visual.ScreenArea;
import javafx.scene.image.Image;

//TODO finish
public class DrawableSpriteSheet extends DrawableImage {
    private Image[] sprites;

    public DrawableSpriteSheet(String sheetPath, ScreenArea screenArea, int priority) {
        super(sheetPath, screenArea, priority);

    }

    public void update(double time) {
        //change current image
    }

}
