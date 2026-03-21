package src.escadasSerpentes.framework;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that draws a scene.
 *
 * @see Scene
 * @see GameRunner
 */
final class GamePanel extends JPanel {
    private final GameRunner runner;

    /**
     * Creates a new game panel.
     *
     * @param runner The runner that uses this panel.
     */
    public GamePanel(GameRunner runner) {
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.runner = runner;
        runner.usePanel(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        runner.drawScene((Graphics2D) g);
    }
}
