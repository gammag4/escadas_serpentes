package src.escadasSerpentes.dto;

import src.escadasSerpentes.IUpdateCardListener;
import src.escadasSerpentes.creators.ICardCreator;

/**
 * Special that randomly generates a new card everytime a player activates it.
 *
 * @see Card
 * @see ISpecial
 */
public class CardSpecial implements ISpecial {
    private final int position;

    private final ICardCreator cardCreator;
    private final IUpdateCardListener createCardListener;

    /**
     * Creates a new card special.
     *
     * @param position Space where this special is.
     * @param cardCreator The card creator.
     * @param createCardListener The object that listens card creation events.
     */
    public CardSpecial(int position, ICardCreator cardCreator, IUpdateCardListener createCardListener) {
        this.position = position;

        this.cardCreator = cardCreator;
        this.createCardListener = createCardListener;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void apply(IPlayer player) {
        Card card = cardCreator.createCard();
        createCardListener.onUpdateCard(card);

        if (player.getPosition() == position) {
            player.move(card.getPositionVariation());
        }
    }
}
