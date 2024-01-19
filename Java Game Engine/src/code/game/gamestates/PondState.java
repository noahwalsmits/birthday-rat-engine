package code.game.gamestates;

import code.engine.GameState;
import code.engine.sound.AudioManager;
import code.engine.visual.Game;
import code.game.FlyCharacter;
import code.game.FrogCharacter;
import javafx.scene.input.KeyEvent;

public class PondState extends GameState {
    private FrogCharacter player;
    private FlyCharacter enemy;

    public PondState(Game game) {
        super(game);
    }

    @Override
    public void enter() {
        //play music and add background

        this.player = new FrogCharacter(900, 500);
        this.enemy = new FlyCharacter(100, 100);
    }

    @Override
    public void exit() {
        this.getGame().clearDrawables();
        AudioManager.getInstance().stopMusic();
    }

    @Override
    public void update(double time) {
        this.player.update(time);
        this.enemy.update(time);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch(event.getCode()) {
            case W:
                this.player.setVerticalInput(1.0);
                break;
            case A:
                this.player.setHorizontalInput(-1.0);
                break;
            case S:
                this.player.setVerticalInput(-1.0);
                break;
            case D:
                this.player.setHorizontalInput(1.0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        switch(event.getCode()) {
            case W:
                this.player.setVerticalInput(0.0);
                break;
            case A:
                this.player.setHorizontalInput(0.0);
                break;
            case S:
                this.player.setVerticalInput(0.0);
                break;
            case D:
                this.player.setHorizontalInput(0.0);
                break;
        }
    }
}
