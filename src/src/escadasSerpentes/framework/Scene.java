package src.escadasSerpentes.framework;

import javax.swing.*;

/**
 * Scene class.
 * <p>
 * Initialize the scene on init() and clean up anything needed on destroy().
 * <p>
 * Implement update() and draw() to update or draw the scene.
 * <p>
 * To draw, use the draw() function or use the drawing panel from getPanel().
 * <p>
 * Use goTo() to go to a new scene.
 * <p>
 * Use getDeltaTime() to get the time between frames.
 *
 * @see GameRunner
 */
public abstract class Scene implements IDrawable, IUpdatable {
    private GameRunner runner;
    private GamePanel panel;

    /**
     * Creates a new scene.
     */
    public Scene() {
        runner = null;
    }

    /**
     * Gets the main panel where graphics will be drawn on.
     *
     * @return The panel.
     */
    protected final JPanel getPanel() {
        return panel;
    }

    /**
     * Changes running scene to a new one.
     * <p>
     * After ending updating or drawing, the current scene will be destroyed,
     * the new scene will be started and the main drawing panel will be cleared.
     *
     * @param newScene The new scene to load.
     */
    protected final void goTo(Scene newScene) {
        runner.goTo(newScene);
    }

    /**
     * Ends the game.
     */
    protected final void endGame() {
        runner.end();
    }

    /**
     * Starts the scene (used by GameRunner).
     *
     * @param runner The runner that runs this scene.
     * @see GameRunner
     */
    final void start(GameRunner runner) {
        this.runner = runner;
        panel = new GamePanel(runner);
        this.init();
    }

    /**
     * Logic for initializing the scene (if any) goes here.
     */
    protected void init() {

    }

    /**
     * Logic for destroying the scene (if any) goes here.
     */
    public void destroy() {

    }
}
