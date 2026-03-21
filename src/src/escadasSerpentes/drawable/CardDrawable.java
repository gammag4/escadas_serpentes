package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.dto.Card;
import src.escadasSerpentes.drawable.components.TextDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Draws a card on the screen.
 */
public class CardDrawable extends DrawableObject {
    private final TextDrawer textDrawer;

    private Card card;
    private String title;
    private String numSpaces;

    private final BufferedImage cardLayout;

    /**
     * Creates a new card drawable.
     * @param resourceProvider The resource provider.
     */
    public CardDrawable(IResourceProvider resourceProvider) {
        textDrawer = new TextDrawer(resourceProvider.getFont(), Color.black);
        setCard(null);

        cardLayout = resourceProvider.getCardLayout();

        this.setScale(7, 7);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
        if (card == null) {
            title = "";
            numSpaces = "";
            return;
        }

        title = card.getGoForward() ? "Anda" : "Volta";
        numSpaces = card.getNumSpaces() + (card.getNumSpaces() > 1 ? " casas" : " casa");
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        if (card == null) {
            return;
        }

        graphics2D.drawImage(cardLayout, 0, 0, null);

        float width = getSize().getFirst() / 2.0f;
        textDrawer.drawCenteredFixedHeight(graphics2D, title, width, getSize().getSecond() * 0.4f, 8f);
        textDrawer.drawCenteredFixedHeight(graphics2D, numSpaces, width, getSize().getSecond() * 0.6f, 5f);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return new Pair<>((float) cardLayout.getWidth(), (float) cardLayout.getHeight());
    }
}
