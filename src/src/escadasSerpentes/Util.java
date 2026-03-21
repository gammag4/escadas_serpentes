package src.escadasSerpentes;

/**
 * Provides useful functions to other classes.
 */
public class Util {
    /**
     * Simple pulse function.
     * @param t The position on time.
     * @return One if 0 <= t <= 1, zero otherwise.
     */
    public static float pulse(float t) {
        if (t < 0 || t > 1) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * A function that mimics jumps.
     * @param t The position on time.
     * @return The jump height.
     */
    public static float jumpFunc(float t) {
        float f1 = 2 - 2 * (1 - t) * (1 - t);
        float f2 = 0.5f - 2 * (2.5f - t) * (2.5f - t);
        float f3 = 0.125f - 2 * (3.25f - t) * (3.25f - t);
        return f1 * pulse(t / 2) + f2 * pulse(3 - t) + f3 * pulse(7 - 2 * t);
    }
}
