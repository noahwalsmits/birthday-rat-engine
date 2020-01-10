package game;

import engine.visual.animation.SpriteSheet;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // write your code here

        try {
            SpriteSheet spriteSheet = new SpriteSheet(ImageIO.read(new File("D:\\Persoonlijk\\Projecten\\Java Game Engine\\resources\\testsheet.png")), 80, 80);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
