package unused.animation;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;
    private int spriteWidth;
    private int spriteHeight;
    private BufferedImage[] sprites;

    public SpriteSheet(BufferedImage image, int spriteWidth, int spriteHeight) {
        this.image = image;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.generateSprites();
    }

    private void generateSprites() {
        boolean counting = true;
        for (int i = 0; i < 2; i++) {
            int index = 0;
            for (int y = 0; y <= this.image.getHeight() - this.spriteHeight; y += this.spriteWidth) {
                for (int x = 0; x <= this.image.getWidth() - this.spriteWidth; x += this.spriteWidth) {
                    if (!counting) {
                        this.sprites[index] = this.image.getSubimage(x, y, this.spriteWidth, this.spriteHeight);
                    }
                    index++;
                }
            }
            if (counting) {
                this.sprites = new BufferedImage[index];
                counting = false;
            }
        }
    }

    public BufferedImage[] getSprites() {
        return this.sprites;
    }

}
