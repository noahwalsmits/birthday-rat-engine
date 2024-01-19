package code.game.gamestates;

import code.engine.GameState;
import code.engine.sound.AudioManager;
import code.engine.visual.Game;
import code.engine.visual.ScreenArea;
import code.engine.visual.ScreenSettings;
import code.game.FlyCharacter;
import code.game.FrogCharacter;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class PondState extends GameState {
    private FrogCharacter player;
    private List<FlyCharacter> enemies;
    private List<FlyCharacter> eatenEnemies;
    private double secondsUntilEnemySpawn = RESPAWN_TIME_SECONDS;

    private static final int STARTING_ENEMY_COUNT = 5;
    private static final int MAX_ENEMY_COUNT = 10;
    private static final double SPAWN_DISTANCE = 400.0;
    private static final double RESPAWN_TIME_SECONDS = 2.0;

    public PondState(Game game) {
        super(game);
    }

    @Override
    public void enter() {
        //TODO play music and add background

        this.player = new FrogCharacter(900, 500);
        this.enemies = new ArrayList<>();
        this.eatenEnemies = new ArrayList<>();
        for (int i = 0; i < STARTING_ENEMY_COUNT; i++) {
            spawnEnemy();
        }
    }

    private void spawnEnemy() {
        int spawnX;
        int spawnY;
        ScreenArea playerArea = this.player.getScreenArea();
        int playerCenterX = (int) (playerArea.getBaseX() + playerArea.getBaseWidth() * 0.5);
        int playerCenterY = (int) (playerArea.getBaseY() + playerArea.getBaseHeight() * 0.5);;
        do {
            spawnX = (int) (Math.random() * ScreenSettings.baseWidth);
            spawnY = (int) (Math.random() * ScreenSettings.baseHeight);
        } while (Math.hypot(spawnX + 50 - playerCenterX, spawnY + 50 - playerCenterY) < SPAWN_DISTANCE);
        this.enemies.add(new FlyCharacter(spawnX, spawnY));
    }

    @Override
    public void exit() {
        this.getGame().clearDrawables();
        AudioManager.getInstance().stopMusic();
    }

    @Override
    public void update(double time) {
        this.player.update(time);

        //update enemies
        for (FlyCharacter enemy : this.enemies) {
            enemy.update(time);
            if (enemy.isEaten(this.player)) {
                //TODO increase score
                this.eatenEnemies.add(enemy);
            }
        }
        this.enemies.removeAll(this.eatenEnemies);
        this.eatenEnemies.clear();

        //tick down respawn timer if there is room for more enemies
        if (this.enemies.size() < MAX_ENEMY_COUNT) {
            this.secondsUntilEnemySpawn -= time;
            if (this.secondsUntilEnemySpawn < 0.0) {
                this.spawnEnemy();
                this.secondsUntilEnemySpawn = RESPAWN_TIME_SECONDS;
            }
        }
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
