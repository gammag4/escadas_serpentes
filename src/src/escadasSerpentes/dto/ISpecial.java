package src.escadasSerpentes.dto;

/**
 * Interface for specials that appear in board spaces.
 */
public interface ISpecial {
    /**
     * Returns the position of the special.
     *
     * @return The position of the special.
     */
    int getPosition();

    /**
     * Applies special to a player.
     *
     * @param player Player to apply special.
     */
    void apply(IPlayer player);
}
