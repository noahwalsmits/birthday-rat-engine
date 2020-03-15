package unused.animation;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @deprecated
 */
public class ErrorResources {
    private static ErrorResources onstance = new ErrorResources();
    private AnimationSet errorAnimation;

    public static ErrorResources getInstance() {
        return onstance;
    }

    private ErrorResources() {
    }

    private void createErrorAnimation() {
        try {
            SpriteSheet spriteSheet = new SpriteSheet(ImageIO.read(new File("D:\\Persoonlijk\\Projecten\\Java Game Engine\\resources\\missing_image.png")), 32, 32);
            this.errorAnimation = new AnimationSet("error", spriteSheet.getSprites());
        } catch (IOException e) {
            System.out.println("Could not create error spritesheet and animation.");
        }
    }

    public AnimationSet getErrorAnimation() {
        return this.errorAnimation;
    }
}
