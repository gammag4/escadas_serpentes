package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.providers.ILineTileProvider;

import java.awt.*;

/**
 * Class for drawing game objects that have linear shapes.
 * <p>
 * These objects should provide a start, end and middle tiles through a ILineTileProvider.
 *
 * @see ILineTileProvider
 */
public class LineDrawable extends DrawableObject {
    private final ILineTileProvider lineTileProvider;

    private int numTiles;

    /**
     * Creates a new line drawable
     * @param lineTileProvider The tile provider.
     */
    public LineDrawable(ILineTileProvider lineTileProvider) {
        this.lineTileProvider = lineTileProvider;
    }

    /**
     * Gets the number of tiles of this line drawable.
     * @return The number of tiles of this line drawable.
     */
    public int getNumTiles() {
        return numTiles;
    }

    /**
     * Sets the number of tiles of this line drawable.
     * @param numTiles The number of tiles of this line drawable.
     */
    public void setNumTiles(int numTiles) {
        this.numTiles = numTiles;
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        int tileSize = lineTileProvider.getMiddleTile().getWidth();

        graphics2D.drawImage(lineTileProvider.getBottomTile(), 0, 0, null);

        int position = tileSize;
        for (int i = 0; i < numTiles; i++) {
            graphics2D.drawImage(lineTileProvider.getMiddleTile(), position, 0, null);
            position += tileSize;
        }

        graphics2D.drawImage(lineTileProvider.getTopTile(), position, 0, null);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return new Pair<>((float) lineTileProvider.getTopTile().getWidth(), (float) lineTileProvider.getTopTile().getHeight());
    }
}
