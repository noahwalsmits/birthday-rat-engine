package code.engine.visual;

import code.engine.GameState;
import code.game.GameInfo;
import code.game.gamestates.MainMenuState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    private Canvas canvas;
    private GameState gameState;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new ResizableCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(this.canvas);
        mainPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        this.canvas.widthProperty().bind(mainPane.widthProperty());
        this.canvas.heightProperty().bind(mainPane.heightProperty());

        Scene scene = new Scene(mainPane, ScreenSettings.getScreenWidth(), ScreenSettings.getScreenHeight());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameState.keyPressed(event);
                if (event.getCode().equals(KeyCode.ESCAPE)) { //TODO remove
                    Platform.exit();
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameState.keyReleased(event);
            }
        });

        primaryStage.setResizable(true);
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
        this.gameState = new MainMenuState(this); //TODO have the starting state be changed somewhere else
        this.gameState.enter();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        RenderManager.getInstance().stop();
        this.gameState.exit();
    }

    private void update(double time) {
        //todo fps counter

        //this.gameLogic.update(time); //todo make asynchronous
        this.gameState.update(time);
    }

    private void draw(GraphicsContext graphics) {
        RenderManager.getInstance().draw();
    }

    public void stateChanged(GameState newState) {
        this.gameState = newState;
        newState.enter();
    }

    public void clearDrawables() {
        RenderManager.getInstance().clearDrawables();
    }

}
