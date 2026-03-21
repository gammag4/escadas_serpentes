package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.framework.IDrawable;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Class for objects that are drawn on screen.
 */
public abstract class DrawableObject implements IDrawable {
    private Pair<Float, Float> position;
    private double scaleX;
    private double scaleY;
    private double rotation;

    /**
     * Creates a new drawable object.
     */
    public DrawableObject() {
        position = new Pair<>(0f, 0f);
        scaleX = 1;
        scaleY = 1;
        rotation = 0;
    }

    /**
     * Gets the object position.
     *
     * @return The object position.
     */
    public Pair<Float, Float> getPosition() {
        return position;
    }

    /**
     * Sets the position.
     *
     * @param x The new position on x-axis.
     * @param y The new position on y-axis.
     */
    public void setPosition(float x, float y) {
        this.position = new Pair<>(x, y);
    }

    /**
     * Gets the scale on the x-axis.
     *
     * @return The scale on the x-axis.
     */
    public double getScaleX() {
        return scaleX;
    }

    /**
     * Gets the scale on the y-axis.
     *
     * @return The scale on the y-axis.
     */
    public double getScaleY() {
        return scaleY;
    }

    /**
     * Sets the scale.
     *
     * @param scaleX The new scale in x-axis.
     * @param scaleY The new scale in y-axis.
     */
    public void setScale(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * Gets the object rotation.
     *
     * @return The rotation.
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Sets the rotation.
     *
     * @param rotation The new rotation.
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * Moves this object a specific amount.
     *
     * @param x The amount to move on x-axis.
     * @param y The amount to move on y-axis.
     */
    public final void translate(float x, float y) {
        this.setPosition(this.getPosition().getFirst() + x, this.getPosition().getSecond() + y);
    }

    /**
     * Rotates this object a specific amount.
     *
     * @param rotation The rotation.
     */
    public final void rotate(double rotation) {
        this.setRotation(this.getRotation() + rotation);
    }

    /**
     * Scales this object a specific amount.
     *
     * @param scaleX The scale on x-axis.
     * @param scaleY The scale on y-axis.
     */
    public final void scale(double scaleX, double scaleY) {
        this.setScale(this.getScaleX() * scaleX, this.getScaleY() * scaleY);
    }

    /**
     * Draws the object starting from the top-left corner.
     *
     * @param graphics2D The graphics object used for drawing this object.
     */
    @Override
    public final void draw(Graphics2D graphics2D) {
        AffineTransform defaultTransform = graphics2D.getTransform();

        AffineTransform transform = new AffineTransform(defaultTransform);
        transform.translate(position.getFirst(), position.getSecond());
        transform.rotate(rotation);
        transform.scale(scaleX, scaleY);
        graphics2D.setTransform(transform);

        drawObject(graphics2D);

        graphics2D.setTransform(defaultTransform);
    }

    /**
     * Draws the object centered.
     *
     * @param graphics2D The graphics object used for drawing this object.
     */
    public final void drawCentered(Graphics2D graphics2D) {
        AffineTransform defaultTransform = graphics2D.getTransform();

        AffineTransform transform = new AffineTransform(defaultTransform);
        transform.translate(position.getFirst(), position.getSecond());
        transform.rotate(rotation);
        transform.scale(scaleX, scaleY);
        transform.translate(-getSize().getFirst() / 2.0, -getSize().getSecond() / 2.0);
        graphics2D.setTransform(transform);

        drawObjectCentered(graphics2D);

        graphics2D.setTransform(defaultTransform);
    }

    /**
     * Logic for drawing the object goes here.
     *
     * @param graphics2D The graphics object used for drawing this object.
     */
    protected abstract void drawObject(Graphics2D graphics2D);

    /**
     * Logic for drawing the object centered goes here.
     * <p>
     * This calls drawObject() by default.
     *
     * @param graphics2D The graphics object used for drawing this object.
     */
    protected void drawObjectCentered(Graphics2D graphics2D) {
        drawObject(graphics2D);
    }

    /**
     * Return the size of the drawable here (used for drawing centered).
     *
     * @return The size of the drawable.
     */
    public Pair<Float, Float> getSize() {
        return new Pair<>(0f, 0f);
    }
}
