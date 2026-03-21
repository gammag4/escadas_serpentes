package src.escadasSerpentes.drawable.animations;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.Util;
import src.escadasSerpentes.drawable.components.TextDrawer;
import src.escadasSerpentes.providers.IResourceProvider;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DieRollAnimation extends Animation {
    private final TextDrawer textDrawer;
    private int result;

    private final BufferedImage dieIcon;
    private final BufferedImage dieLayout;

    public DieRollAnimation(IResourceProvider resourceProvider) {
        super(5.0f);

        textDrawer = new TextDrawer(resourceProvider.getFont(), Color.black);
        result = 0;

        dieIcon = resourceProvider.getDieIcon();
        dieLayout = resourceProvider.getDieLayout();

        setScale(3, 3);
    }

    public void start(int result) {
        this.result = result;
        this.start();
    }

    @Override
    protected void drawAnimation(Graphics2D graphics2D, float animationTime) {
        if (animationTime > 2) {
            graphics2D.drawImage(dieLayout, 0, 0, null);
            textDrawer.drawCenteredFixedHeight(graphics2D, Integer.toString(result), dieLayout.getWidth() / 2f, dieLayout.getHeight() / 2f, 12f);
            return;
        }

        float t = animationTime * 3;
        float y = Util.jumpFunc(t) * 30;

        graphics2D.drawImage(dieIcon, 0, (int) -y, null);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return new Pair<>((float) dieLayout.getWidth(), (float) dieLayout.getHeight());
    }
}
