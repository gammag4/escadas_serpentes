package src.escadasSerpentes.framework;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;

/**
 * Class for running the game.
 *
 * @see Scene
 */
public final class GameRunner {
    private Scene currentScene;
    private Scene nextScene;
    private boolean shouldEnd;

    private final Dimension startSize;
    private final JFrame window;

    private final Object threadLock;
    private boolean changingScene;
    private boolean drawing;

    private Duration deltaTime;
    private Instant lastTime;

    /**
     * Creates a new game runner.
     *
     * @param title  Game window title.
     * @param width  Game window width.
     * @param height Game window height.
     */
    public GameRunner(String title, int width, int height) {
        nextScene = null;
        shouldEnd = false;
        startSize = new Dimension(width, height);
        threadLock = new Object();
        changingScene = false;
        drawing = false;

        deltaTime = Duration.ZERO;
        lastTime = Instant.EPOCH;

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(200, 200));
        window.setPreferredSize(startSize);
        window.setTitle(title);
    }

    /**
     * Starts the game loop thread.
     *
     * @param startingScene The starting scene to run.
     * @see Scene
     */
    public void run(Scene startingScene) {
        window.pack();
        window.setLocationRelativeTo(null);

        currentScene = startingScene;
        currentScene.start(this);

        window.setVisible(true);

        deltaTime = Duration.ZERO;
        lastTime = Instant.now();

        while (!shouldEnd) {
            currentScene.update(getDeltaTime());
            window.repaint();

            updateCurrentScene();

            updateDeltaTime();
        }

        currentScene.destroy();
    }

    // Changes the current scene to the new scene if there is a new one.
    private void updateCurrentScene() {
        if (nextScene == null)
            return;

        synchronized (threadLock) {
            if (drawing)
                return;
            changingScene = true;
        }

        currentScene.destroy();
        currentScene = nextScene;
        currentScene.start(this);
        nextScene = null;

        changingScene = false;
    }

    // Updates current deltatime.
    private void updateDeltaTime() {
        var nextTime = Instant.now();
        deltaTime = Duration.between(lastTime, nextTime);
        lastTime = nextTime;
    }

    /**
     * Draws current scene synchronized (used by GamePanel).
     *
     * @param graphics2D The graphics used for drawing the scene.
     * @see GamePanel
     */
    void drawScene(Graphics2D graphics2D) {
        synchronized (threadLock) {
            if (changingScene)
                return;
            drawing = true;
        }

        currentScene.draw(graphics2D);
        drawing = false;
    }

    /**
     * Sets window panel to use.
     *
     * @param panel The panel to use.
     */
    public void usePanel(JPanel panel) {
        window.setContentPane(panel);
        window.validate();
    }

    public double getDeltaTime() {
        return ((double) deltaTime.toNanos()) / 1000_000_000L;
    }

    /**
     * Checks whether the game loop has ended.
     */
    public boolean isEnded() {
        return shouldEnd;
    }

    /**
     * Changes running scene to a new one.
     *
     * @param scene The new scene to run.
     * @see Scene
     */
    public void goTo(Scene scene) {
        nextScene = scene;
    }

    /**
     * Ends running the scenes.
     */
    public void end() {
        shouldEnd = true;
    }
}
