package engine.visual.drawable;

import com.sun.istack.internal.Nullable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The information needed for drawing text in a certain way can be stored in this object.
 */
public class TextInfo {
    private Font font;
    private Color fillColor;
    private Color strokeColor;
    private double lineWidth;

    public TextInfo(Font font, @Nullable Color fillColor, @Nullable Color strokeColor, double lineWidth) {
        this.font = font;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.lineWidth = lineWidth;
    }

    public TextInfo(Font font, @Nullable Color fillColor, @Nullable Color strokeColor) {
        this(font, fillColor, strokeColor, 1.0);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setFontSize(double size) {
        this.font = new Font(this.font.getName(), size); //TODO test
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }
}
