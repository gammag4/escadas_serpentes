package src.escadasSerpentes.controllers;

import src.escadasSerpentes.drawable.animations.PlayerJumpAnimation;
import src.escadasSerpentes.drawable.components.TextDrawer;
import src.escadasSerpentes.dto.IPlayer;
import src.escadasSerpentes.framework.Scene;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.providers.ResourceProvider;

import java.awt.*;

public class WinnerScene extends Scene {
    private final IPlayer winner;

    private PlayerJumpAnimation animation;

    private TextDrawer textDrawer;

    private IResourceProvider resourceProvider;

    public WinnerScene(IPlayer winner) {
        this.winner = winner;
    }

    @Override
    protected void init() {
        getPanel().setBackground(Color.white);
        resourceProvider = ResourceProvider.getInstance();

        textDrawer = new TextDrawer(resourceProvider.getFont(), Color.black);

        animation = new PlayerJumpAnimation(winner, resourceProvider);
        animation.start();

        animation.setScale(1.5, 1.5);
    }

    @Override
    public void update(double deltaTime) {
        animation.setPosition(getPanel().getWidth() / 2f, getPanel().getHeight() / 2f);
        animation.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        animation.drawCentered(graphics2D);
        textDrawer.drawCenteredFixedHeight(graphics2D, "Parabens, voce venceu!", getPanel().getWidth() / 2f, 60, 60);
    }
}
