package engine.visual.drawable;

import engine.visual.screen.ScreenArea;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawableImage extends Drawable {
    private Image image;
    private String imagePath;

    /**
     * Creates a drawable, keep in mind that it will still need to be added to the render manager for it to be drawn
     *
     * @param screenArea The area on the screen the drawable should take up
     * @param imagePath  The path to the image that should be drawn, should lead to an image file in the resources folder
     * @param priority   The priority compared to other drawables, higher priority drawables get drawn on top of lower priority ones
     */
    public DrawableImage(String imagePath, ScreenArea screenArea, int priority) {
        super(screenArea, priority);
        if (screenArea != null) {
            this.image = this.generateImage(imagePath, screenArea);
        }
        this.imagePath = imagePath;
    }

    /**
     * Draw the image on the screen
     *
     * @param graphics A graphics context to draw on, is provided by RenderManager
     */
    @Override
    public void draw(GraphicsContext graphics) {
        graphics.drawImage(this.image, super.getScreenArea().getX(), super.getScreenArea().getY());
    }

    /**
     * Changes the ScreenArea and generates a new image. Use this method to change the size of the image.
     * For moving the image it faster to move the ScreenArea the Drawable was already using.
     *
     * @param screenArea The new ScreenArea
     */
    @Override
    public void setScreenArea(ScreenArea screenArea) {
        super.setScreenArea(screenArea);
        this.image = this.generateImage(this.imagePath, screenArea);
    }

    private Image generateImage(String imagePath, ScreenArea screenArea) {
        Image generatedImage;
        try {
            generatedImage = new Image(getClass().getResource(imagePath).toString(),
                    (double) screenArea.getWidth(),
                    (double) screenArea.getHeight(),
                    false,
                    true); //true = better quality resize || false = faster resize
        } catch (NullPointerException e) {
            System.out.println("Drawable: Could not find image");
            generatedImage = new Image(getClass().getResource("/images/engine/missing_image.png").toString(),
                    (double) screenArea.getWidth(),
                    (double) screenArea.getHeight(),
                    false,
                    true); //true = better quality resize || false = faster resize
            e.printStackTrace();
        }
        return generatedImage;
    }
}
