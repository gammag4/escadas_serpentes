package src.escadasSerpentes.dto;

import com.sun.jdi.Value;

/**
 * Player class.
 */
public class Player implements IPlayer {
    private final int id;
    private final String name;
    private final int lastSpace;
    private int position;

    /**
     * Creates a new player
     *
     * @param name Player name.
     */
    public Player(int id, String name, int lastSpace) {
        this.id = id;
        this.name = name;
        this.lastSpace = lastSpace;
        this.position = 0;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public int getLastSpace() {
        return lastSpace;
    }

    @Override
    public void move(int numSpaces) {
        int newPosition = this.position + numSpaces;

        this.position = Math.min(newPosition, lastSpace);
    }
}
