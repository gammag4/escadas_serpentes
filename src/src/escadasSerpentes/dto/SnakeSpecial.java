package src.escadasSerpentes.dto;

/**
 * Special that moves a player from the position of the
 * snake head to the position of its tail when activated.
 *
 * @see ISpecial
 */
public class SnakeSpecial implements ISpecial {
    private final int head;
    private final int tail;

    /**
     * Creates a new snake special.
     *
     * @param head Head position (the same position as the special).
     * @param tail Tail position.
     */
    public SnakeSpecial(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Returns head position.
     *
     * @return Head position.
     */
    public int getHead() {
        return head;
    }

    /**
     * Returns tail position.
     *
     * @return Tail position.
     */
    public int getTail() {
        return tail;
    }

    @Override
    public int getPosition() {
        return head;
    }

    @Override
    public void apply(IPlayer player) {
        if (player.getPosition() == head) {
            player.move(tail - head);
        }
    }
}
