package engine;

import engine.visual.Game;
import javafx.scene.input.KeyEvent;

public abstract class GameState {
    private Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public abstract void init();

    public abstract void update(double time);

    public abstract void keyPressed(KeyEvent event);

    public abstract void keyReleased(KeyEvent event);

    public void changeState(GameState newState) {
        this.game.stateChanged(newState);
        this.game = null;
    }

    public Game getGame() {
        return game;
    }
}
