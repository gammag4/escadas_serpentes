package src.escadasSerpentes.providers;

import java.awt.image.BufferedImage;

/**
 * Provides tiles for a line drawable to draw.
 *
 * @see src.escadasSerpentes.drawable.LineDrawable
 */
public class LineTileProvider implements ILineTileProvider {
    private final BufferedImage top;
    private final BufferedImage middle;
    private final BufferedImage bottom;

    /**
     * Creates a new line tile provider.
     *
     * @param top    The top tile.
     * @param middle The middle tile.
     * @param bottom The bottom tile.
     */
    public LineTileProvider(BufferedImage top, BufferedImage middle, BufferedImage bottom) {
        this.top = top;
        this.middle = middle;
        this.bottom = bottom;
    }

    @Override
    public BufferedImage getTopTile() {
        return top;
    }

    @Override
    public BufferedImage getMiddleTile() {
        return middle;
    }

    @Override
    public BufferedImage getBottomTile() {
        return bottom;
    }
}
