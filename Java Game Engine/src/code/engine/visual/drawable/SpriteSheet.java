package code.engine.visual.drawable;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;

//TODO finish
public class SpriteSheet {
    private Image[] sprites;

    public SpriteSheet(Image sheet, int frameCount, int spriteWidth, int spriteHeight) {
        this.sprites = createSprites(sheet, frameCount, spriteWidth, spriteHeight);
    }

    private Image[] createSprites(Image sheet, int frameCount, int spriteWidth, int spriteHeight) {
        PixelReader pixelReader = sheet.getPixelReader();
        List<Image> images = new ArrayList<>();

        for (int x = 0; x < sheet.getWidth(); x+=spriteWidth) {
            for (int y = 0; y < sheet.getHeight(); y+=spriteHeight) {
                WritableImage writableImage = new WritableImage(pixelReader, x, y, spriteWidth, spriteHeight);
                images.add(writableImage);
                if(images.size() == frameCount) {
                    return (Image[]) images.toArray();
                }
            }
        }

        return (Image[]) images.toArray();
    }

}
