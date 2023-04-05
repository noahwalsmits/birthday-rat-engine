package code.engine.visual.drawable;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.Nullable;

/**
 * The information needed for drawing text in a certain way can be stored in this object.
 */
public class TextInfo {
    private Font font;
    private TextAlignment textAlignment;
    private VPos baseline;
    private Color fillColor;
    private Color strokeColor;
    private double lineWidth;

    private boolean xCentered;
    private boolean yCentered;

    public TextInfo(Font font, TextAlignment textAlignment, VPos baseline, @Nullable Color fillColor, @Nullable Color strokeColor, double lineWidth) {
        this.font = font;
        this.textAlignment = textAlignment;
        this.baseline = baseline;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.lineWidth = lineWidth;
        this.updateCentered();
    }

//    public TextInfo(Font font, @Nullable Color fillColor, @Nullable Color strokeColor) {
//        this(font, TextAlignment.CENTER,fillColor, strokeColor, 1.0);
//    }
    //TODO use fonts from files

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setFontSize(double size) {
        this.font = new Font(this.font.getName(), size); //TODO test
    }

    public TextAlignment getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
        this.updateCentered();
    }

    public VPos getBaseline() {
        return baseline;
    }

    public void setBaseline(VPos baseline) {
        this.baseline = baseline;
        this.updateCentered();
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

    boolean isxCentered() {
        return xCentered;
    }

    boolean isyCentered() {
        return yCentered;
    }

    private void updateCentered() {
        if (this.textAlignment == TextAlignment.CENTER) {
            this.xCentered = true;
        } else {
            this.xCentered = false;
        }

        if (this.baseline == VPos.CENTER) {
            this.yCentered = true;
        } else {
            this.yCentered = false;
        }
    }
}
