package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.dto.CardSpecial;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Draws a card special on the screen.
 */
public class CardSpecialDrawable extends SpecialDrawable {
    private final CardSpecial special;

    private final BufferedImage cardIcon;

    /**
     * Creates a new card special drawable.
     * @param special The card special to draw.
     * @param resourceProvider The resource provider.
     */
    public CardSpecialDrawable(CardSpecial special, IResourceProvider resourceProvider) {
        this.special = special;

        this.cardIcon = resourceProvider.getCardIcon();
    }

    @Override
    public void setPositionFromBoard(BoardGridDrawable boardGridDrawable) {
        float spaceSize = boardGridDrawable.computeSpaceSideLength();
        Pair<Float, Float> position = boardGridDrawable.computeSpacePositionCenter(special.getPosition());
        this.setPosition(position.getFirst() + spaceSize / 4, position.getSecond() - spaceSize / 4);
        this.translate(boardGridDrawable.getPosition().getFirst(), boardGridDrawable.getPosition().getSecond());

        double tileScale = boardGridDrawable.computeBoardScale();
        this.setScale(tileScale, tileScale);
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        graphics2D.drawImage(cardIcon, 0, 0, null);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return new Pair<>((float) cardIcon.getWidth(), (float) cardIcon.getHeight());
    }
}
