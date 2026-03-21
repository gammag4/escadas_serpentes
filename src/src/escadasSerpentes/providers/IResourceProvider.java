package src.escadasSerpentes.providers;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IResourceProvider {
    /**
     * Loads the ttf font (if not loaded before) and provides the loaded font.
     */
    Font getFont();

    /**
     * Provides the text color.
     */
    Color getTextColor();

    /**
     * Provides the outlined text color.
     */
    Color getOutlinedTextColor();

    /**
     * Provides the outlined text outline color.
     */
    Color getOutlinedTextOutlineColor();

    /**
     * Provides the color of the board outline.
     */
    Color getBoardOutlineColor();

    /**
     * Provides background color for panels.
     */
    Color getBackgroundColor();

    /**
     * Provides card icon.
     */
    BufferedImage getCardIcon();

    /**
     * Provides card layout image.
     */
    BufferedImage getCardLayout();

    /**
     * Provides die icon.
     */
    BufferedImage getDieIcon();

    /**
     * Provides die layout image.
     */
    BufferedImage getDieLayout();

    /**
     * Provides ladder top tile image.
     */
    BufferedImage getLadderTop();

    /**
     * Provides ladder middle tile image.
     */
    BufferedImage getLadderMiddle();

    /**
     * Provides ladder bottom tile image.
     */
    BufferedImage getLadderBottom();

    /**
     * Provides snake head tile image.
     */
    BufferedImage getSnakeHead();

    /**
     * Provides snake body tile image.
     */
    BufferedImage getSnakeBody();

    /**
     * Provides snake tail tile image.
     */
    BufferedImage getSnakeTail();

    /**
     * Provides pawn blue image.
     */
    BufferedImage getPawnBlue();

    /**
     * Provides pawn green image.
     */
    BufferedImage getPawnGreen();

    /**
     * Provides pawn red image.
     */
    BufferedImage getPawnRed();

    /**
     * Provides pawn yellow image.
     */
    BufferedImage getPawnYellow();
}
