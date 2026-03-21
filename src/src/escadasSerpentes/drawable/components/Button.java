package src.escadasSerpentes.drawable.components;

import src.escadasSerpentes.providers.IResourceProvider;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Customized button class.
 */
public class Button extends JButton {
    /**
     * Creates a new button.
     *
     * @param text             The button text.
     * @param actionListener   The action listener that listens for clicks on this button.
     * @param resourceProvider The resource provider.
     */
    public Button(String text, ActionListener actionListener, IResourceProvider resourceProvider) {
        super(text);

        addActionListener(actionListener);
        setBackground(Color.white);
        setFont(resourceProvider.getFont());
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBorderPainted(true);

        computeBorders(2, 3);
    }

    public void setBorderThickness(int borderThickness, int padding) {
        computeBorders(borderThickness, padding);
    }

    private void computeBorders(int borderThickness, int padding) {
        Color backgroudColor = Color.white;
        Color clickColor = new Color(227, 248, 255,255);

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, borderThickness),
                BorderFactory.createLineBorder(backgroudColor, padding)
        );
        Border clickBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, borderThickness),
                BorderFactory.createLineBorder(clickColor, padding)
        );
        setBorder(border);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) {
                    setBorder(clickBorder);
                    setBackground(clickColor);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBorder(border);
                setBackground(backgroudColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
}
