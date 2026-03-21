package src.escadasSerpentes.providers;

import java.awt.image.BufferedImage;

/**
 * Provides tiles for a line drawable to draw.
 *
 * @see src.escadasSerpentes.drawable.LineDrawable
 */
public interface ILineTileProvider {
    /**
     * Gets the top tile of the line special.
     */
    BufferedImage getTopTile();

    /**
     * Gets the middle tile of the line special.
     */
    BufferedImage getMiddleTile();

    /**
     * Gets the bottom tile of the line special.
     */
    BufferedImage getBottomTile();
}
