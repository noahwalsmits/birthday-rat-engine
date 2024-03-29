package code.engine.visual.drawable;

import code.engine.visual.Drawable;
import code.engine.visual.ScreenArea;
import javafx.scene.canvas.GraphicsContext;

public class DrawableText extends Drawable {
    private String text;
    private TextInfo textInfo;

    private double xOffset;
    private double yOffset;

    public DrawableText(String text, TextInfo textInfo, ScreenArea screenArea, int priority) {
        super(screenArea, priority);
        this.text = text;
        this.textInfo = textInfo;
        this.show();
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.setFont(this.textInfo.getFont());
        graphics.setTextAlign(this.textInfo.getTextAlignment());
        graphics.setTextBaseline(this.textInfo.getBaseline());
        graphics.setFill(this.textInfo.getFillColor());
        graphics.setStroke(this.textInfo.getStrokeColor());
        graphics.setLineWidth(this.textInfo.getLineWidth());

        if (this.textInfo.isxCentered()) {
            this.xOffset = super.getScreenArea().getWidth() / 2;
        } else {
            this.xOffset = 0.0;
        }

        if (this.textInfo.isyCentered()) {
            this.yOffset = super.getScreenArea().getHeight() / 2;
        } else {
            this.yOffset = super.getScreenArea().getHeight();
        }

        if (textInfo.getFillColor() != null) {
            graphics.fillText(this.text,
                    super.getScreenArea().getX() + xOffset,
                    super.getScreenArea().getY() + yOffset,
                    super.getScreenArea().getX() + super.getScreenArea().getWidth());
        }

        if (textInfo.getStrokeColor() != null) {
            graphics.strokeText(this.text,
                    super.getScreenArea().getX() + xOffset,
                    super.getScreenArea().getY() + yOffset,
                    super.getScreenArea().getX() + super.getScreenArea().getWidth());
        }
    }

    @Override
    public void resize() {
        this.textInfo.setFontSize(this.getScreenArea().getHeight()); //convert pixels to points
        //TODO have font size be based on screenarea
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextInfo getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(TextInfo textInfo) {
        this.textInfo = textInfo;
    }
}
