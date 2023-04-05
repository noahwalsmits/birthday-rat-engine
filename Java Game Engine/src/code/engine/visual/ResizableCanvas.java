package code.engine.visual;

import javafx.scene.canvas.Canvas;

public class ResizableCanvas extends Canvas {

    public ResizableCanvas() {
        RenderManager.getInstance().setGraphics(super.getGraphicsContext2D());
        //redraw canvas when size changes.
        widthProperty().addListener(evt -> resized());
        heightProperty().addListener(evt -> resized());
    }

    private void resized() {
        //RenderManager.getInstance().draw();
        ScreenSettings.setScreenWidth((int) super.getWidth());
        ScreenSettings.setScreenHeight((int) super.getHeight());
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double v) {
        return super.getWidth();
    }

    @Override
    public double prefHeight(double v) {
        return super.getHeight();
    }
}
