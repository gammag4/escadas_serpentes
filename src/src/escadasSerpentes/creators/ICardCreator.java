package src.escadasSerpentes.creators;

import src.escadasSerpentes.dto.Card;

/**
 * Interface for creating new cards.
 */
public interface ICardCreator {
    /**
     * Creates a new randomly generated card.
     */
    Card createCard();
}
