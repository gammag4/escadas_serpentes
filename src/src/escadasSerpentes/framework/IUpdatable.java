package src.escadasSerpentes.framework;

/**
 * Updatable interface.
 */
public interface IUpdatable {
    /**
     * Logic for updating the object (if any) goes here.
     *
     * @param deltaTime The duration in seconds between the current frame and the previous one.
     */
    default void update(double deltaTime) {

    }
}
