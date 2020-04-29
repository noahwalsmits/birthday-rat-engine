package engine.visual.drawable;

import engine.visual.screen.ScreenArea;
import javafx.scene.canvas.GraphicsContext;

public class DrawableText extends Drawable {
    private String text;
    private TextInfo textInfo;

    public DrawableText(String text, TextInfo textInfo, ScreenArea screenArea, int priority) {
        super(screenArea, priority);
        this.text = text;
        this.textInfo = textInfo;
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.setFont(this.textInfo.getFont());
        graphics.setFill(this.textInfo.getFillColor());
        graphics.setStroke(this.textInfo.getStrokeColor());
        graphics.setLineWidth(this.textInfo.getLineWidth());

        if (textInfo.getFillColor() != null) {
            graphics.fillText(this.text, super.getScreenArea().getX(),
                    super.getScreenArea().getY() + super.getScreenArea().getHeight(),
                    super.getScreenArea().getX() + super.getScreenArea().getWidth());
        }

        if (textInfo.getStrokeColor() != null) {
            graphics.strokeText(this.text, super.getScreenArea().getX(),
                    super.getScreenArea().getY() + super.getScreenArea().getHeight(),
                    super.getScreenArea().getX() + super.getScreenArea().getWidth());
        }
    }//TODO draw text in the middle of the drawable

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
