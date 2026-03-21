package src.escadasSerpentes.drawable;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.drawable.components.TextDrawer;
import src.escadasSerpentes.dto.IPlayer;
import src.escadasSerpentes.providers.IResourceProvider;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerDrawable extends DrawableObject {
    private final TextDrawer textDrawer;

    private final String name;
    private final BufferedImage image;

    public PlayerDrawable(IPlayer player, IResourceProvider resourceProvider) {
        textDrawer = new TextDrawer(resourceProvider.getFont().deriveFont(10f), Color.black);
        name = player.getName();

        switch (player.getID() % 4) {
            case 1 -> image = resourceProvider.getPawnGreen();
            case 2 -> image = resourceProvider.getPawnRed();
            case 3 -> image = resourceProvider.getPawnYellow();
            default -> image = resourceProvider.getPawnBlue();
        }
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        textDrawer.drawCenteredLabeled(graphics2D, name, image.getWidth() / 2f, -15f - image.getWidth() / 2f, Color.black, Color.white);
        graphics2D.drawImage(image, 0, -image.getWidth() / 2, null);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return new Pair<>((float) image.getWidth(), (float) image.getHeight());
    }
}
