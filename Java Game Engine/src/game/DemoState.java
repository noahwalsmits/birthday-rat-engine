package game;

import engine.GameState;
import engine.sound.AudioManager;
import engine.visual.Game;
import engine.visual.RenderManager;
import engine.visual.drawable.Drawable;
import engine.visual.screen.ScreenArea;
import javafx.scene.input.KeyEvent;

public class DemoState extends GameState {

    public DemoState(Game game) {
        super(game);
    }

    @Override
    public void init() {
        Drawable drawable = new Drawable("/images/testsheet.png", new ScreenArea(100, 100, 700, 500), 10);
        RenderManager.getInstance().addDrawable(drawable);

        AudioManager.getInstance().playMusic("/sound/Intermission.mp3", false);

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println(event.toString());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        System.out.println(event.toString());
        super.changeState(new DemoState(super.getGame()));
    }
}
