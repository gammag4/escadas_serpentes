package src.escadasSerpentes.dto;

/**
 * Card class that makes the player go forward or backward a specific number of spaces.
 *
 * @see CardSpecial
 */
public class Card {
    private final boolean goForward;
    private final int numSpaces;

    /**
     * Creates a new card.
     *
     * @param goForward Whether the card makes someone go forward or backward.
     * @param numSpaces Number of spaces to walk.
     */
    public Card(boolean goForward, int numSpaces) {
        this.goForward = goForward;
        this.numSpaces = numSpaces;
    }

    /**
     * Returns whether the card makes someone go forward or backward.
     *
     * @return Whether the card makes someone go forward or backward.
     */
    public boolean getGoForward() {
        return goForward;
    }

    /**
     * Returns number of spaces to walk.
     *
     * @return Number of spaces to walk.
     */
    public int getNumSpaces() {
        return numSpaces;
    }

    /**
     * Gets the variation in the player position, where negative means walking backwards.
     *
     * @return The variation in the player position.
     */
    public int getPositionVariation() {
        return goForward ? numSpaces : -numSpaces;
    }
}
