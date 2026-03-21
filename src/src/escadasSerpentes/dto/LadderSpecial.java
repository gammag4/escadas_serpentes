package src.escadasSerpentes.dto;

/**
 * Special that when activated moves a player from the position
 * on the bottom of the ladder to the position on the top of it.
 *
 * @see ISpecial
 */
public class LadderSpecial implements ISpecial {
    private final int bottom;
    private final int top;

    /**
     * Creates a new ladder special.
     *
     * @param bottom Bottom position (the same position as the special).
     * @param top    Top position.
     */
    public LadderSpecial(int bottom, int top) {
        this.bottom = bottom;
        this.top = top;
    }

    /**
     * Returns bottom position.
     *
     * @return Bottom position.
     */
    public int getBottom() {
        return bottom;
    }

    /**
     * Returns top position.
     *
     * @return Top position.
     */
    public int getTop() {
        return top;
    }

    @Override
    public int getPosition() {
        return bottom;
    }

    @Override
    public void apply(IPlayer player) {
        if (player.getPosition() == bottom) {
            player.move(top - bottom);
        }
    }
}
