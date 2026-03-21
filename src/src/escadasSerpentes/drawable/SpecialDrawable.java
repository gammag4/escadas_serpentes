package src.escadasSerpentes.drawable;

/**
 * Class used for drawing specials.
 */
public abstract class SpecialDrawable extends DrawableObject {
    /**
     * Sets the special position by using board position and size.
     * @param boardGridDrawable The board grid drawable to get the measurements from.
     */
    public abstract void setPositionFromBoard(BoardGridDrawable boardGridDrawable);
}
