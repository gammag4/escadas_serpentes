package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.dto.Board;
import src.escadasSerpentes.drawable.components.TextDrawer;

import java.awt.*;

/**
 * Draws a board grid on the screen.
 */
public class BoardGridDrawable extends DrawableObject {
    private final Color color1 = new Color(255, 255, 255);
    private final Color color2 = new Color(152, 228, 255);
    private final Color outlineColor = Color.black;

    private final Board board;

    private final TextDrawer textDrawer;

    private Dimension size;

    /**
     * Creates a new board grid drawable.
     * @param board The board to draw.
     * @param resourceProvider The resource provider.
     */
    public BoardGridDrawable(Board board, IResourceProvider resourceProvider) {
        this.board = board;

        textDrawer = new TextDrawer(resourceProvider.getFont(), resourceProvider.getTextColor());

        size = new Dimension();
    }

    @Override
    public Pair<Float, Float> getSize() {
        float sideLength = computeBoardSideLength();
        return new Pair<>(sideLength, sideLength);
    }

    /**
     * Sets the size of the drawable.
     * @param size The size.
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public void drawObject(Graphics2D graphics2D) {
        for (int i = 0; i < board.getNumSpaces(); i++) {
            drawSpace(graphics2D, i);
        }
    }

    private void drawSpace(Graphics2D graphics2D, int spaceNumber) {
        Pair<Float, Float> SpacePosition = computeSpacePosition(spaceNumber);

        drawSpace(graphics2D, SpacePosition, spaceNumber);
    }

    public void drawSpace(Graphics2D graphics2D, Pair<Float, Float> position, int spaceNumber) {
        float spaceSize = computeSpaceSideLength();

        int x = Math.round(position.getFirst());
        int y = Math.round(position.getSecond());

        int sx = Math.round(spaceSize + position.getFirst() - x);
        int sy = Math.round(spaceSize + position.getSecond() - y);

        // Draw space.
        Color color = getSpaceColor(spaceNumber);
        graphics2D.setColor(color);
        graphics2D.fillRect(x, y, sx, sy);

        // Draw outline.
        graphics2D.setColor(outlineColor);
        graphics2D.drawRect(x, y, sx - 1, sy - 1);

        // Draw number.
        String text = Integer.toString(spaceNumber + 1);
        float fontX = x + (spaceSize / 2);
        float fontY = y + (spaceSize / 2);

        textDrawer.drawCenteredFixedHeight(graphics2D, text, fontX, fontY, spaceSize / 2);
    }

    public float computeSpaceSideLength() {
        // Computes size of each space and gets the smallest one.
        float incrementX = (float) (size.getWidth() / board.getColumns());
        float incrementY = (float) (size.getHeight() / board.getRows());
        return Math.min(incrementX, incrementY);
    }

    public Pair<Float, Float> computeSpacePosition(int spaceNumber) {
        float spaceSize = computeSpaceSideLength();

        int xeven = (spaceNumber / board.getColumns() + 1) % 2; // 1 if x is even, 0 otherwise.
        int xodd = (spaceNumber / board.getColumns()) % 2; // 1 if x is odd, 0 otherwise.
        float oddPos = ((spaceNumber % board.getColumns()) * spaceSize); // Position goes from left to right.
        float evenPos = ((board.getColumns() - (spaceNumber % board.getColumns()) - 1) * spaceSize); // Position goes from right to left.

        float positionX = oddPos * xeven + evenPos * xodd; // Alternates each line between going rtl or ltr.
        float positionY = (Math.floorDiv(board.getNumSpaces() - spaceNumber - 1, board.getColumns()) * spaceSize); // Position goes from bottom to top.

        return new Pair<>(positionX, positionY);
    }

    public Pair<Float, Float> computeSpacePositionCenter(int spaceNumber) {
        float spaceSize = computeSpaceSideLength();

        Pair<Float, Float> result = computeSpacePosition(spaceNumber);
        result.setFirst(result.getFirst() + spaceSize / 2);
        result.setSecond(result.getSecond() + spaceSize / 2);
        return result;
    }

    public float computeBoardSideLength() {
        float spaceSize = computeSpaceSideLength();
        return board.getColumns() * spaceSize;
    }

    public double computeBoardScale() {
        return computeSpaceSideLength() / 64; // Default size for each board tile is 64.
    }

    public Color getSpaceColor(int spaceNumber) {
        return (spaceNumber % 2 == 0) ? color1 : color2; // Alternates between colors.
    }
}
