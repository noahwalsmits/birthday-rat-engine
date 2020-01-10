package engine.visual;

import engine.GameLogic;
import engine.visual.screen.ScreenSettings;
import game.DemonstrationGameLogic;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
    private Canvas canvas;
    private GameLogic gameLogic;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(ScreenSettings.screenWidth, ScreenSettings.screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        RenderManager.getInstance().setGraphics(gc);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(this.canvas);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, ScreenSettings.screenWidth, ScreenSettings.screenHeight));
        primaryStage.setTitle("Game");
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

    public void init() {
        this.gameLogic = new DemonstrationGameLogic();
        this.gameLogic.init();
    }

    public void draw(GraphicsContext graphics) {
        RenderManager.getInstance().draw();
    }

    public void update(double time) {
        RenderManager.getInstance().asyncUpdate(time);
        this.gameLogic.update(time); //todo make asynchronous
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        RenderManager.getInstance().stop();
    }
}
