package src.escadasSerpentes.drawable.components;

import src.escadasSerpentes.dto.PlayerRowData;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.dto.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlayerRow extends JPanel {
    private final int id;
    private final JTextField nameField;

    public PlayerRow(IResourceProvider resourceProvider, int playerNumber) {
        id = playerNumber;

        this.setLayout(new FlowLayout());
        this.setBackground(new Color(0, 0, 0, 0));

        JLabel nameLabel = new JLabel("Nome do jogador " + id + ":");
        nameLabel.setFont(resourceProvider.getFont());
        nameLabel.setForeground(resourceProvider.getTextColor());

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, 2),
                BorderFactory.createLineBorder(Color.white, 2)
        );
        nameField = new JTextField(25);
        nameField.setFont(resourceProvider.getFont());
        nameField.setBorder(border);

        this.add(nameLabel);
        this.add(nameField);
    }

    public PlayerRowData getPlayerData() {
        return new PlayerRowData(id, nameField.getText());
    }
}
