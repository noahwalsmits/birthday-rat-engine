package game.gamestates;

import engine.GameState;
import engine.sound.AudioManager;
import engine.visual.Game;
import engine.visual.RenderManager;
import engine.visual.drawable.DrawableImage;
import engine.visual.screen.ScreenArea;
import javafx.scene.input.KeyEvent;

public class MainMenuState extends GameState {
    private DrawableImage dStart;
    private DrawableImage dOptions;
    private DrawableImage dExit;
    private DrawableImage dHighlight;
    private int buttonIndex;

    public MainMenuState(Game game) {
        super(game);
        this.buttonIndex = 0;
    }

    @Override
    public void enter() {
        this.dStart = new DrawableImage("/images/demo/startgame.png", new ScreenArea(1600, 100, 300, 100), 10);
        this.dOptions = new DrawableImage("/images/demo/options.png", new ScreenArea(1600, 300, 300, 100), 10);
        this.dExit = new DrawableImage("/images/demo/exitgame.png", new ScreenArea(1600, 500, 300, 100), 10);
        this.dHighlight = new DrawableImage("/images/demo/highlight.png", null, 20);

        this.updateHighlight();

        RenderManager.getInstance().addDrawable(this.dStart);
        RenderManager.getInstance().addDrawable(this.dOptions);
        RenderManager.getInstance().addDrawable(this.dExit);
        RenderManager.getInstance().addDrawable(this.dHighlight);

        DrawableImage background = new DrawableImage("/images/demo/background.png", new ScreenArea(0, 0, 1920, 1080), 0);
        RenderManager.getInstance().addDrawable(background);
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
                AudioManager.getInstance().playSound("/sound/nope.mp3");
                break;
            case S:
                this.changeButtonIndex(1);
                AudioManager.getInstance().playSound("/sound/nope.mp3");
                break;
            case ENTER:
                AudioManager.getInstance().playSound("/sound/Intermission.mp3");
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
        switch (this.buttonIndex) {
            case 0:
                this.dHighlight.setScreenArea(this.dStart.getScreenArea());
                break;
            case 1:
                this.dHighlight.setScreenArea(this.dOptions.getScreenArea());
                break;
            case 2:
                this.dHighlight.setScreenArea(this.dExit.getScreenArea());
                break;
            default:
                this.dHighlight.setScreenArea(new ScreenArea(0, 0, 0, 0));
                break;
        }
    }
}