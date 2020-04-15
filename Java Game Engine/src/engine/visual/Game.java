package engine.visual;

import engine.GameState;
import engine.visual.screen.ScreenSettings;
import game.DemoState;
import game.GameInfo;
import game.gamestates.MainMenuState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
    private Canvas canvas;
    private GameState gameState;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(ScreenSettings.screenWidth, ScreenSettings.screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        RenderManager.getInstance().setGraphics(gc);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(this.canvas);

        Scene scene = new Scene(mainPane, ScreenSettings.screenWidth, ScreenSettings.screenHeight);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameState.keyPressed(event);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameState.keyReleased(event);
            }
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle(GameInfo.WINDOW_TITLE);
        primaryStage.show();

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(gc);
            }
        }.start();
    }

    @Override
    public void init() {
        //this.gameLogic = new DemonstrationGameLogic();
        //this.gameLogic.init();
        //TODO demo state
        this.gameState = new MainMenuState(this); //TODO have the starting state be changed somewhere else
        this.gameState.enter();
    }

    public void update(double time) {
        RenderManager.getInstance().asyncUpdate(time);
        //this.gameLogic.update(time); //todo make asynchronous
        this.gameState.update(time);
    }

    public void draw(GraphicsContext graphics) {
        RenderManager.getInstance().draw();
    }

    public void stateChanged(GameState newState) {
        this.gameState = newState;
        newState.enter();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        RenderManager.getInstance().stop();
        this.gameState.exit();
    }
}
