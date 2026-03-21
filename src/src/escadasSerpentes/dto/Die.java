package src.escadasSerpentes.dto;

import src.escadasSerpentes.framework.RandomProvider;

import java.util.Random;

/**
 * Die class.
 */
public class Die {
    /**
     * Rolls the die.
     * @return The number after the roll.
     */
    public int roll() {
        Random random = RandomProvider.getRandom();
        return random.nextInt(6) + 1;
    }
}
