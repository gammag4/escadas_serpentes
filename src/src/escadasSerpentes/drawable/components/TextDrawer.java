package src.escadasSerpentes.drawable.components;

import java.awt.*;
import java.util.Objects;

public class TextDrawer {
    private Font font;
    private Color color;

    public TextDrawer(Font font, Color color) {
        this.font = font;
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawCenteredLabeled(Graphics2D graphics2D, String text, float x, float y, Color borderColor, Color labelFillColor) {
        if (Objects.equals(text, "")) {
            return;
        }

        FontMetrics metrics = graphics2D.getFontMetrics(font);
        int width = metrics.stringWidth(text);
        int height = metrics.getHeight();

        graphics2D.setColor(borderColor);
        graphics2D.fillRect((int) x - width / 2 - 6, (int) y - height / 2 - 4, width + 12, height + 8);

        graphics2D.setColor(labelFillColor);
        graphics2D.fillRect((int) x - width / 2 - 4, (int) y - height / 2 - 2, width + 8, height + 4);

        drawCentered(graphics2D, text, x, y);
    }

    public void drawCenteredFixedWidth(Graphics2D graphics2D, String text, float x, float y, float width) {
        Font defaultFont = font;

        float size = getSizeFromWidth(graphics2D, text, width);
        font = font.deriveFont(size);

        drawCentered(graphics2D, text, x, y);
        font = defaultFont;
    }

    public void drawCenteredFixedHeight(Graphics2D graphics2D, String text, float x, float y, float height) {
        Font defaultFont = font;

        float size = getSizeFromHeight(graphics2D, height);
        font = font.deriveFont(size);

        drawCentered(graphics2D, text, x, y);
        font = defaultFont;
    }

    public void drawCentered(Graphics2D graphics2D, String text, float x, float y) {
        graphics2D.setFont(font);
        graphics2D.setColor(color);

        FontMetrics metrics = graphics2D.getFontMetrics();

        // Centers text width and height.
        int mHeight = metrics.getAscent() - metrics.getDescent() - metrics.getLeading();
        float fontX = x - metrics.stringWidth(text) / 2.0f;
        float fontY = y + mHeight / 2.0f;

        graphics2D.drawString(text, fontX, fontY);
    }

    private float getSizeFromWidth(Graphics2D graphics2D, String text, float width) {
        FontMetrics metrics = graphics2D.getFontMetrics(font);
        float strWidth = (float) metrics.stringWidth(text);

        return font.getSize2D() * width / strWidth;
    }

    private float getSizeFromHeight(Graphics2D graphics2D, float height) {
        FontMetrics metrics = graphics2D.getFontMetrics(font);
        int strHeight = metrics.getHeight();

        return font.getSize2D() * height / strHeight;
    }
}
