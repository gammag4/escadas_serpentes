package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.providers.ILineSpecialProvider;

import java.awt.*;

/**
 * Class for drawing specials that have linear shapes.
 *
 * @see LineDrawable
 */
public class LineSpecialDrawable extends SpecialDrawable {
    private final ILineSpecialProvider special;
    private final LineDrawable drawable;

    private Pair<Float, Float> sizeVector;

    /**
     * Creates a new line special drawable.
     * @param special The special provider.
     */
    public LineSpecialDrawable(ILineSpecialProvider special) {
        this.special = special;
        drawable = new LineDrawable(special);

        sizeVector = new Pair<>(0f, 0f);
    }

    @Override
    public void setPositionFromBoard(BoardGridDrawable boardGridDrawable) {
        Pair<Float, Float> top = boardGridDrawable.computeSpacePositionCenter(special.getTopPosition());
        Pair<Float, Float> bottom = boardGridDrawable.computeSpacePositionCenter(special.getBottomPosition());
        sizeVector = new Pair<>(top.getFirst() - bottom.getFirst(), (float) top.getSecond() - bottom.getSecond());

        // Position.
        this.setPosition(bottom.getFirst(), bottom.getSecond());
        this.translate(boardGridDrawable.getPosition().getFirst(), boardGridDrawable.getPosition().getSecond());

        // Rotation.
        double rotation = Math.atan2(sizeVector.getSecond(), sizeVector.getFirst());
        this.setRotation(rotation);

        // Scale.
        double tileScale = boardGridDrawable.computeBoardScale();
        double yscale = sizeVector.getFirst() > 0 ? tileScale : -tileScale; // Flips the special if it is backwards.
        this.setScale(tileScale, yscale);

        double distance = Math.sqrt(sizeVector.getFirst() * sizeVector.getFirst() + sizeVector.getSecond() * sizeVector.getSecond());

        int tileSize = special.getMiddleTile().getWidth();
        int numTiles = (int) Math.round(distance / (tileSize * getScaleX())) - 1; // Minus one because both ends sum up to one tile.
        drawable.setNumTiles(numTiles);
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        drawable.draw(graphics2D);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return drawable.getSize();
    }
}
