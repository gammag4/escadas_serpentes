package src.escadasSerpentes;

/**
 * Pair class.
 * @param <A> The first value type.
 * @param <B> The second value type.
 */
public class Pair<A, B> {
    private A a;
    private B b;

    /**
     * Creates a new pair.
     * @param first First value.
     * @param second Second value.
     */
    public Pair(A first, B second) {
        this.a = first;
        this.b = second;
    }

    /**
     * Gets first value of the pair.
     * @return The first value.
     */
    public A getFirst() {
        return a;
    }

    public void setFirst(A a) {
        this.a = a;
    }

    /**
     * Gets second value of the pair.
     * @return The second value.
     */
    public B getSecond() {
        return b;
    }

    public void setSecond(B b) {
        this.b = b;
    }
}
