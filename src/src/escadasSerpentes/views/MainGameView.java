package src.escadasSerpentes.views;

import src.escadasSerpentes.IGameStateChangeListener;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.drawable.components.Button;
import src.escadasSerpentes.framework.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGameView implements ActionListener, IView {
    private JPanel parentPanel;
    private final IResourceProvider resourceProvider;
    private final IGameStateChangeListener gameStateChangeListener;

    private final Button continueGameButton;

    public MainGameView(JPanel parentPanel, IResourceProvider resourceProvider, IGameStateChangeListener gameStateChangeListener) {
        this.parentPanel = parentPanel;
        this.resourceProvider = resourceProvider;
        this.gameStateChangeListener = gameStateChangeListener;

        parentPanel.setLayout(new BorderLayout());

        continueGameButton = new Button("Iniciar jogo", this, resourceProvider);
        continueGameButton.setFont(continueGameButton.getFont().deriveFont(24f));
        continueGameButton.setBorderThickness(4, 5);

        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        JPanel panel = new JPanel(layout);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.add(continueGameButton);
        layout.setHgap(20);
        layout.setVgap(20);

        parentPanel.add(panel, BorderLayout.SOUTH);
        parentPanel.revalidate();
    }

    public JButton getContinueGameButton() {
        return continueGameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == continueGameButton) {
            gameStateChangeListener.onContinueGame();
        }
    }
}
