package code.game.gamestates;

import code.engine.GameState;
import code.engine.sound.AudioManager;
import code.engine.visual.Game;
import code.game.FlyCharacter;
import code.game.FrogCharacter;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class PondState extends GameState {
    private FrogCharacter player;
    private List<FlyCharacter> enemies;
    private List<FlyCharacter> eatenEnemies;

    public PondState(Game game) {
        super(game);
    }

    @Override
    public void enter() {
        //TODO play music and add background

        this.player = new FrogCharacter(900, 500);
        this.enemies = new ArrayList<>();
        this.enemies.add(new FlyCharacter(100, 100));
        this.enemies.add(new FlyCharacter(300, 100));
        this.enemies.add(new FlyCharacter(500, 100));
        this.enemies.add(new FlyCharacter(700, 100));
        this.enemies.add(new FlyCharacter(900, 100));
        this.eatenEnemies = new ArrayList<>();
    }

    @Override
    public void exit() {
        this.getGame().clearDrawables();
        AudioManager.getInstance().stopMusic();
    }

    @Override
    public void update(double time) {
        this.player.update(time);

        for (FlyCharacter enemy : this.enemies) {
            enemy.update(time);
            if (enemy.isEaten(this.player)) {
                //TODO increase score
                this.eatenEnemies.add(enemy);
            }
        }
        this.enemies.removeAll(this.eatenEnemies);
        this.eatenEnemies.clear();
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
