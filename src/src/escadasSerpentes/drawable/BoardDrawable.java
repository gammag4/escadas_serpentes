package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;

import java.awt.*;
import java.util.List;

/**
 * Draws a board on the screen.
 */
public class BoardDrawable extends DrawableObject {
    BoardGridDrawable boardGrid;
    List<SpecialDrawable> specials;

    /**
     * Creates a new board drawable.
     * @param boardGrid The board grid drawable.
     * @param specials The list of special drawables.
     */
    public BoardDrawable(BoardGridDrawable boardGrid, List<SpecialDrawable> specials) {
        this.boardGrid = boardGrid;
        this.specials = specials;

        updateSpecialsPositions();
    }

    public BoardGridDrawable getBoardGrid() {
        return boardGrid;
    }

    private void updateSpecialsPositions() {
        for (SpecialDrawable special : specials) {
            special.setPositionFromBoard(boardGrid);
        }
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        boardGrid.draw(graphics2D);
        for (SpecialDrawable special : specials) {
            special.drawCentered(graphics2D);
        }
    }

    @Override
    public Pair<Float, Float> getSize() {
        return boardGrid.getSize();
    }

    /**
     * Sets the size of the drawable.
     * @param size The size.
     */
    public void setSize(Dimension size) {
        boardGrid.setSize(size);
        updateSpecialsPositions();
    }
}
