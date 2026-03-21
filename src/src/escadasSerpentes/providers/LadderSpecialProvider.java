package src.escadasSerpentes.providers;

import src.escadasSerpentes.dto.LadderSpecial;

import java.awt.image.BufferedImage;

/**
 * Provides ladder special, positions and tiles for a line special drawable to draw.
 *
 * @see src.escadasSerpentes.drawable.LineSpecialDrawable
 */
public class LadderSpecialProvider implements ILineSpecialProvider {
    private final LadderSpecial special;

    ILineTileProvider tileProvider;

    /**
     * Creates a new ladder special provider.
     *
     * @param special      The ladder special to draw.
     * @param tileProvider The tile provider that provides the tiles.
     */
    public LadderSpecialProvider(LadderSpecial special, ILineTileProvider tileProvider) {
        this.special = special;
        this.tileProvider = tileProvider;
    }

    /**
     * Creates a new ladder special provider.
     *
     * @param special The ladder special to draw.
     * @param top     The top tile.
     * @param middle  The middle tile.
     * @param bottom  The bottom tile.
     */
    public LadderSpecialProvider(LadderSpecial special, BufferedImage top, BufferedImage middle, BufferedImage bottom) {
        this.special = special;
        this.tileProvider = new LineTileProvider(top, middle, bottom);
    }

    @Override
    public int getTopPosition() {
        return special.getTop();
    }

    @Override
    public int getBottomPosition() {
        return special.getBottom();
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
