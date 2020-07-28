package game.gamestates;

import engine.GameState;
import engine.sound.AudioManager;
import engine.visual.Game;
import engine.visual.drawable.DrawableImage;
import engine.visual.drawable.DrawableText;
import engine.visual.drawable.TextInfo;
import engine.visual.ScreenArea;
import javafx.geometry.VPos;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainMenuState extends GameState {
    private int buttonIndex;
    private int selectedButtonOffset = 0;
    private double timePassed = 0.0;

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
        //play music
        AudioManager.getInstance().playMusic("/game/sound/rainsong.mp3", true);

        //create background
        DrawableImage background = new DrawableImage("/game/images/mainmenu.jpg", new ScreenArea(0, 0, 1920, 1080), 0);
        background.show();

        //set button locations
        this.saStart = new ScreenArea(-200, 100, 700, 100);
        this.saOptions = new ScreenArea(-200, 300, 700, 100);
        this.saExit = new ScreenArea(-200, 500, 700, 100);
        this.saStartText = new ScreenArea(50, 100, 700, 100);
        this.saOptionsText = new ScreenArea(50, 300, 700, 100);
        this.saExitText = new ScreenArea(50, 500, 700, 100);

        //create button backgrounds
        DrawableImage diStart = new DrawableImage("/game/images/buttonBackground.png", saStart, 10);
        DrawableImage diOptions = new DrawableImage("/game/images/buttonBackground.png", saOptions, 10);
        DrawableImage diExit = new DrawableImage("/game/images/buttonBackground.png", saExit, 10);
        diStart.show();
        diOptions.show();
        diExit.show();

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
        dtStart.show();
        dtOptions.show();
        dtExit.show();
    }

    @Override
    public void exit() {
        this.getGame().clearDrawables();
    }

    @Override
    public void update(double time) {
        if (this.selectedButtonOffset < 100) {
            this.timePassed += time;
            if (this.timePassed > 0.01) {
                this.timePassed = 0.0;
                int moveDistance = (int)(time * 1000);
                this.selectedButtonOffset += moveDistance;
                switch (this.buttonIndex) {
                    case 0:
                        this.saStart.move(moveDistance, 0);
                        this.saStartText.move(moveDistance, 0);
                        break;
                    case 1:
                        this.saOptions.move(moveDistance, 0);
                        this.saOptionsText.move(moveDistance, 0);
                        break;
                    case 2:
                        this.saExit.move(moveDistance, 0);
                        this.saExitText.move(moveDistance, 0);
                        break;
                    default:

                        break;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                this.changeButtonIndex(-1);
                AudioManager.playSound("/game/sound/bush.wav");
                break;
            case S:
                this.changeButtonIndex(1);
                AudioManager.playSound("/game/sound/bush.wav");
                break;
            case ENTER:
                AudioManager.playSound("/game/sound/frog1.wav", 2.0, 0.7, 0.0, 0);
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
        this.saStart.moveTo(-200, 100);
        this.saStartText.moveTo(50, 100);
        this.saOptions.moveTo(-200, 300);
        this.saOptionsText.moveTo(50, 300);
        this.saExit.moveTo(-200, 500);
        this.saExitText.moveTo(50, 500);
        this.selectedButtonOffset = 0;

    }
}