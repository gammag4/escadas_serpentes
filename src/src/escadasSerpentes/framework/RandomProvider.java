package src.escadasSerpentes.framework;

import java.util.Random;

/**
 * Class that provides a unique random generator instance across all the game.
 * <p>
 * This avoids two random generators that are created at the same
 * time to have the same seeds and, therefore, the same outputs.
 */
public class RandomProvider {
    private static Random random;

    public static Random getRandom() {
        if (random == null) {
            random = new Random();
        }

        return random;
    }
}
