package engine;

import engine.visual.Game;
import javafx.scene.input.KeyEvent;

public abstract class GameState {
    private Game game;

    public GameState(Game game) {
        this.game = game;
    }

    /**
     * Gets called on the new GameState when the state is changed, and when the game starts
     */
    public abstract void enter();

    /**
     * Gets called on the old GameState when the state is changed, and when the game is closed
     */
    public abstract void exit();

    /**
     * Gets called by the Game after every update
     *
     * @param time The amount of seconds since the last update.
     */
    public abstract void update(double time);

    public abstract void keyPressed(KeyEvent event);

    public abstract void keyReleased(KeyEvent event);

    /**
     * Changes the state of the game by sending a new state to the Game object.
     * The current state will no longer be used after the state has been changed.
     * The old state will execute its exit() method when the state is changed.
     * The new state will execute its enter() method when the state is changed.
     *
     * @param newState The new state to change to.
     */
    public void changeState(GameState newState) {
        this.game.stateChanged(newState);
        this.exit();
        this.game = null;
    }

    public Game getGame() {
        return game;
    }
}
