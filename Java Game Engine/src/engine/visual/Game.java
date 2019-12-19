package engine.visual;

import engine.ResourceReader;
import engine.sound.AudioManager;
import engine.visual.drawable.Drawable;
import engine.visual.drawable.Layer;
import engine.visual.screen.ScreenArea;
import engine.visual.screen.ScreenSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Game extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(this.canvas);
        FXGraphics2D graphics = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        RenderManager.getInstance().setGraphics(graphics);

        primaryStage.setScene(new Scene(mainPane, ScreenSettings.screenWidth, ScreenSettings.screenHeight));
        primaryStage.setTitle("Game");
        primaryStage.show();
        draw(graphics);

        this.testDraw();//
    }

    public void init() {
        AudioManager.getInstance();
    }

    public void draw(FXGraphics2D graphics) {
        RenderManager.getInstance().draw();
    }

    public void testDraw() {//
        try {//
            Layer layer = new Layer("ground");//
            Drawable drawable = new Drawable(
                    new ScreenArea(10, 20, 200, 100),
                    ImageIO.read(new File("D:\\Persoonlijk\\Projecten\\Java Game Engine\\resources\\testsheet.png")));//image
            layer.getDrawables().add(drawable);
            RenderManager.getInstance().getLayers().add(layer);
            //RenderManager.getInstance().draw();
        } catch (IOException e) {
            e.printStackTrace();
        }//
    }
}
