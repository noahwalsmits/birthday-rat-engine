package game.gamestates;

import engine.GameState;
import engine.sound.AudioManager;
import engine.visual.Game;
import engine.visual.ScreenArea;
import engine.visual.drawable.DrawableImage;
import engine.visual.drawable.DrawableText;
import engine.visual.drawable.TextInfo;
import javafx.geometry.VPos;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class StickBugState extends GameState {
    public StickBugState(Game game) {
        super(game);
    }

    @Override
    public void enter() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                stickbug();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void exit() {

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private void stickbug() {
        ScreenArea screenArea = new ScreenArea(910, 540, 100, 200);

        //create shared text info
        TextInfo textInfo = new TextInfo(new Font("calibri", 50.0),
                TextAlignment.CENTER,
                VPos.CENTER,
                Color.WHITE,
                Color.BLACK,
                1.5);

        DrawableText drawableText = new DrawableText("Loading", textInfo, screenArea, 10);
        drawableText.show();

        for (int i = 0; i < 3; i++) {
            try {
                drawableText.setText("Loading");
                Thread.sleep(1000);
                drawableText.setText("Loading.");
                Thread.sleep(1000);
                drawableText.setText("Loading..");
                Thread.sleep(1000);
                drawableText.setText("Loading...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

        ScreenArea backgroundArea = new ScreenArea(0, 0, 1920, 1080);
        DrawableImage drawableImage = new DrawableImage("/game/images/hqdefault.png", backgroundArea, 0);
        drawableImage.show();

        drawableText.setText("YOU HAVE BEEN STICK BUGGED LOL");
        AudioManager.getInstance().playMusic("/game/sound/GET STICK BUGGED LOL.mp3", true);
    }
}
