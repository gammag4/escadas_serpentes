package src.escadasSerpentes.providers;

import src.escadasSerpentes.dto.SnakeSpecial;

import java.awt.image.BufferedImage;

/**
 * Provides snake special, positions and tiles for a line special drawable to draw.
 *
 * @see src.escadasSerpentes.drawable.LineSpecialDrawable
 */
public class SnakeSpecialProvider implements ILineSpecialProvider {
    private final SnakeSpecial special;

    ILineTileProvider tileProvider;

    /**
     * Creates a new snake special provider.
     *
     * @param special      The snake special to draw.
     * @param tileProvider The tile provider that provides the tiles.
     */
    public SnakeSpecialProvider(SnakeSpecial special, ILineTileProvider tileProvider) {
        this.special = special;
        this.tileProvider = tileProvider;
    }

    /**
     * Creates a new snake special provider.
     *
     * @param special The snake special to draw.
     * @param top     The top tile.
     * @param middle  The middle tile.
     * @param bottom  The bottom tile.
     */
    public SnakeSpecialProvider(SnakeSpecial special, BufferedImage top, BufferedImage middle, BufferedImage bottom) {
        this.special = special;
        this.tileProvider = new LineTileProvider(top, middle, bottom);
    }

    @Override
    public int getTopPosition() {
        return special.getHead();
    }

    @Override
    public int getBottomPosition() {
        return special.getTail();
    }

    @Override
    public BufferedImage getTopTile() {
        return tileProvider.getTopTile();
    }

    @Override
    public BufferedImage getMiddleTile() {
        return tileProvider.getMiddleTile();
    }

    @Override
    public BufferedImage getBottomTile() {
        return tileProvider.getBottomTile();
    }
}
