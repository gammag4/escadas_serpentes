package src.escadasSerpentes.dto;

/**
 * Interface for Players.
 */
public interface IPlayer {
    /**
     * Returns player ID.
     *
     * @return Player ID.
     */
    int getID();

    /**
     * Returns player name.
     *
     * @return Player name.
     */
    String getName();

    /**
     * Returns player position.
     *
     * @return Player position.
     */
    int getPosition();

    /**
     * Moves the player the specified number of spaces (negative moves backwards).
     *
     * @param numSpaces Number of spaces to move (negative moves backwards).
     */
    void move(int numSpaces);
}
