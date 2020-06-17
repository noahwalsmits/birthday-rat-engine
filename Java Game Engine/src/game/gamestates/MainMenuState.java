package game.gamestates;

import engine.GameState;
import engine.sound.AudioManager;
import engine.visual.Game;
import engine.visual.RenderManager;
import engine.visual.drawable.DrawableImage;
import engine.visual.drawable.DrawableText;
import engine.visual.drawable.TextInfo;
import engine.visual.screen.ScreenArea;
import javafx.geometry.VPos;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainMenuState extends GameState {
    private int buttonIndex;

    private ScreenArea saStart;
    private ScreenArea saOptions;
    private ScreenArea saExit;
    private ScreenArea saStartText;
    private ScreenArea saOptionsText;
    private ScreenArea saExitText;

    public MainMenuState(Game game) {
        super(game);
        this.buttonIndex = 0;
    }

    @Override
    public void enter() {
        //AudioManager.getInstance().playMusic("/sound/rainsong.mp3", true);

        //create background
        DrawableImage background = new DrawableImage("/images/demo/mainmenu.jpg", new ScreenArea(0, 0, 1920, 1080), 0);
        RenderManager.getInstance().addDrawable(background);

        //set button locations
        this.saStart = new ScreenArea(-200, 100, 700, 100);
        this.saOptions = new ScreenArea(-200, 300, 700, 100);
        this.saExit = new ScreenArea(-200, 500, 700, 100);
        this.saStartText = new ScreenArea(50, 100, 700, 100);
        this.saOptionsText = new ScreenArea(50, 300, 700, 100);
        this.saExitText = new ScreenArea(50, 500, 700, 100);

        //create button backgrounds
        DrawableImage diStart = new DrawableImage("/images/demo/buttonBackground.png", saStart, 10);
        DrawableImage diOptions = new DrawableImage("/images/demo/buttonBackground.png", saOptions, 10);
        DrawableImage diExit = new DrawableImage("/images/demo/buttonBackground.png", saExit, 10);
        RenderManager.getInstance().addDrawable(diStart);
        RenderManager.getInstance().addDrawable(diOptions);
        RenderManager.getInstance().addDrawable(diExit);

        //create shared text info
        TextInfo textInfo = new TextInfo(new Font("calibri", 50.0),
                TextAlignment.LEFT,
                VPos.CENTER,
                Color.BLACK,
                null,
                1.0);

        //create button texts
        DrawableText dtStart = new DrawableText("Start", textInfo, this.saStartText, 20);
        DrawableText dtOptions = new DrawableText("Options", textInfo, this.saOptionsText, 20);
        DrawableText dtExit = new DrawableText("Exit", textInfo, this.saExitText, 20);
        RenderManager.getInstance().addDrawable(dtStart);
        RenderManager.getInstance().addDrawable(dtOptions);
        RenderManager.getInstance().addDrawable(dtExit);

        this.updateHighlight();
    }

    @Override
    public void exit() {
        RenderManager.getInstance().clearDrawables();
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                this.changeButtonIndex(-1);
                AudioManager.getInstance().playSound("/sound/bush.mp3");
                break;
            case S:
                this.changeButtonIndex(1);
                AudioManager.getInstance().playSound("/sound/bush.mp3");
                break;
            case ENTER:
                AudioManager.getInstance().playSound("/sound/Intermission.mp3");
                break;
            default:

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private void changeButtonIndex(int change) {
        int min = 0;
        int max = 2;

        if (change > 0) {
            this.buttonIndex++;
        } else if (change < 0) {
            this.buttonIndex--;
        }

        if (this.buttonIndex < min) {
            this.buttonIndex = max;
        } else if (this.buttonIndex > max) {
            this.buttonIndex = min;
        }
        this.updateHighlight();
    }

    private void updateHighlight() {
//        switch (this.buttonIndex) {
//            case 0:
//                this.dHighlight.setScreenArea(this.dStart.getScreenArea());
//                break;
//            case 1:
//                this.dHighlight.setScreenArea(this.dOptions.getScreenArea());
//                break;
//            case 2:
//                this.dHighlight.setScreenArea(this.dExit.getScreenArea());
//                break;
//            default:
//                this.dHighlight.setScreenArea(new ScreenArea(0, 0, 0, 0));
//                break;
//        }
    }
}