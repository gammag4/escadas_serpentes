package src.escadasSerpentes;

import src.escadasSerpentes.dto.Card;

/**
 * Interface that listens for a change on the current showing card.
 */
public interface IUpdateCardListener {
    /**
     * Action fired when the current card is updated.
     * @param card The updated card or null if the card is being removed.
     */
    void onUpdateCard(Card card);
}
