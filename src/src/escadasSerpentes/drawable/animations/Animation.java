package src.escadasSerpentes.drawable.animations;

import src.escadasSerpentes.drawable.DrawableObject;

import java.awt.*;

/**
 * Class for drawing animations.
 */
public abstract class Animation extends DrawableObject {
    private final float duration;

    private float animationTime;
    private boolean continueAnimation;

    /**
     * Creates a new animation.
     * @param duration The total duration of the animation (if negative, the animation never stops).
     */
    public Animation(float duration) {
        this.duration = duration;
    }

    /**
     * Creates a new endless animation.
     */
    public Animation() {
        this.duration = -1;
    }

    /**
     * Updates the animation
     * @param deltaTime The delta time between this frame and the last one.
     */
    public void update(double deltaTime) {
        if (duration >= 0 && animationTime > duration) {
            stop();
        }
        if (continueAnimation) {
            animationTime += deltaTime;
        }
    }

    /**
     * Starts the animation (or restarts it if it's already running.
     */
    public void start() {
        animationTime = 0;
        continueAnimation = true;
    }

    /**
     * Stops the animation.
     */
    public void stop() {
        continueAnimation = false;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }

    /**
     * Checks whether the animation should continue running or not.
     * @return Whether the animation should continue running or not.
     */
    public boolean shouldContinueAnimation() {
        return continueAnimation;
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        if (continueAnimation) {
            drawAnimation(graphics2D, animationTime);
        }
    }

    @Override
    protected void drawObjectCentered(Graphics2D graphics2D) {
        if (continueAnimation) {
            drawAnimationCentered(graphics2D, animationTime);
        }
    }

    /**
     * Draws the animation.
     * @param graphics2D The graphics object used when drawing the animation.
     * @param animationTime The position on time the animation is in.
     */
    protected abstract void drawAnimation(Graphics2D graphics2D, float animationTime);

    /**
     * Draws the animation centered.
     * @param graphics2D The graphics object used when drawing the animation.
     * @param animationTime The position on time the animation is in.
     */
    protected void drawAnimationCentered(Graphics2D graphics2D, float animationTime) {
        super.drawObjectCentered(graphics2D);
    }
}
