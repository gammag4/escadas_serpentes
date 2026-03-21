package src.escadasSerpentes.views;

import src.escadasSerpentes.ICreatePlayersListener;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.drawable.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePlayersView implements ActionListener {

    private final JPanel listPanel;

    private final JButton addRowButton;
    private final JButton removeRowButton;
    private final JButton confirmButton;

    private final ICreatePlayersListener createPlayersListener;

    public CreatePlayersView(JPanel parentPanel, IResourceProvider resourceProvider, ICreatePlayersListener createPlayersListener) {
        this.createPlayersListener = createPlayersListener;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 0, 0, 0));

        JLabel label = new JLabel("Escadas e Serpentes");
        label.setForeground(resourceProvider.getTextColor());
        label.setFont(resourceProvider.getFont().deriveFont(50f));

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(0, 0, 0, 0));

        addRowButton = new Button("Adicionar", this, resourceProvider);
        removeRowButton = new Button("Remover", this, resourceProvider);
        confirmButton = new Button("Jogar!", this, resourceProvider);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(0, 0, 0, 0));
        buttonsPanel.add(addRowButton);
        buttonsPanel.add(removeRowButton);
        buttonsPanel.add(confirmButton);

        panel.add(label);
        panel.add(listPanel);
        panel.add(buttonsPanel);

        parentPanel.setBackground(resourceProvider.getBackgroundColor());
        parentPanel.add(panel);
        parentPanel.revalidate();
    }

    public JButton getAddRowButton() {
        return addRowButton;
    }

    public JButton getRemoveRowButton() {
        return removeRowButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JPanel getListPanel() {
        return listPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == addRowButton) {
            createPlayersListener.onAddRow();
        } else if (src == removeRowButton) {
            createPlayersListener.onRemoveRow();
        } else if (src == confirmButton) {
            createPlayersListener.onConfirm();
        }
    }
}
