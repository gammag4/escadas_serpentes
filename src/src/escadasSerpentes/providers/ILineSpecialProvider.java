package src.escadasSerpentes.providers;

/**
 * Provides positions and tiles for a line special drawable to draw.
 *
 * @see src.escadasSerpentes.drawable.LineSpecialDrawable
 */
public interface ILineSpecialProvider extends ILineTileProvider {
    /**
     * Gets the top position of the line special.
     */
    int getTopPosition();

    /**
     * Gets the bottom position of the line special.
     */
    int getBottomPosition();
}
