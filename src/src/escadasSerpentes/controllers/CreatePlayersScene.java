package src.escadasSerpentes.controllers;

import src.escadasSerpentes.*;
import src.escadasSerpentes.dto.PlayerRowData;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.providers.ResourceProvider;
import src.escadasSerpentes.framework.Scene;
import src.escadasSerpentes.views.CreatePlayersView;
import src.escadasSerpentes.drawable.components.PlayerRow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreatePlayersScene extends Scene implements ICreatePlayersListener {
    private final int minPlayers;
    private final int maxPlayers;

    private final IResourceProvider resourceProvider;

    private final List<PlayerRow> rows;
    private CreatePlayersView view;

    public CreatePlayersScene() {
        minPlayers = 2;
        maxPlayers = 4;

        resourceProvider = ResourceProvider.getInstance();

        rows = new ArrayList<>();
    }

    @Override
    public void onAddRow() {
        JPanel listPanel = view.getListPanel();
        if (listPanel.getComponentCount() < maxPlayers) {
            var row = new PlayerRow(resourceProvider, listPanel.getComponentCount() + 1);
            listPanel.add(row);
            listPanel.revalidate();
            rows.add(row);
        }

        view.getAddRowButton().setEnabled(listPanel.getComponentCount() < maxPlayers);
        view.getRemoveRowButton().setEnabled(listPanel.getComponentCount() > minPlayers);
    }

    @Override
    public void onRemoveRow() {
        JPanel listPanel = view.getListPanel();
        if (listPanel.getComponentCount() > minPlayers) {
            listPanel.remove(listPanel.getComponentCount() - 1);
            listPanel.revalidate();
            rows.remove(rows.size() - 1);
        }

        view.getAddRowButton().setEnabled(listPanel.getComponentCount() < maxPlayers);
        view.getRemoveRowButton().setEnabled(listPanel.getComponentCount() > minPlayers);
    }

    @Override
    public void onConfirm() {
        List<PlayerRowData> players = new ArrayList<>();
        for (PlayerRow row : rows) {
            players.add(row.getPlayerData());
        }

        goTo(new MainGameScene(players));
    }

    @Override
    protected void init() {
        view = new CreatePlayersView(getPanel(), resourceProvider, this);

        // Adds first players
        for (int i = 0; i < minPlayers; i++) {
            onAddRow();
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {

    }
}
