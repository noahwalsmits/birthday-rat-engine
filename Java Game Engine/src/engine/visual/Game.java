package engine.visual;

import engine.visual.drawable.Drawable;
import engine.visual.screen.ScreenArea;
import engine.visual.screen.ScreenSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
    private Canvas canvas;

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

        draw(gc);
    }

    public void init() {
        RenderManager.getInstance().addDrawable(new Drawable(new ScreenArea(0, 0, 100, 100), "/images/testsheet.png"));
    }

    public void draw(GraphicsContext graphics) {
        RenderManager.getInstance().draw();
    }

}
