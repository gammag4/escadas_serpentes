package src.escadasSerpentes.framework;

import java.awt.*;

/**
 * Drawable interface.
 * <p>
 * Implement draw() to draw the object.
 */
public interface IDrawable {
    /**
     * Logic for drawing the object (if any) goes here.
     *
     * @param graphics2D The object used for drawing graphics.
     */
    default void draw(Graphics2D graphics2D) {

    }
}
