package src.escadasSerpentes.other;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.providers.ILineTileProvider;
import src.escadasSerpentes.drawable.DrawableObject;
import src.escadasSerpentes.drawable.LineDrawable;
import src.escadasSerpentes.framework.IUpdatable;

import java.awt.*;

public class RandomSpecialDrawable extends DrawableObject implements IUpdatable {
    LineDrawable drawable;
    private final double rotationSpeed;
    private final double sineSpeed;
    private final double minScale;
    private final double maxScale;

    private double sinePosition;

    public RandomSpecialDrawable(ILineTileProvider lineTileProvider, double rotationSpeed, double sineSpeed, int numTiles, double minScale, double maxScale) {
        drawable = new LineDrawable(lineTileProvider);
        this.rotationSpeed = rotationSpeed;
        this.sineSpeed = sineSpeed;
        this.minScale = minScale;
        this.maxScale = maxScale;

        drawable.setNumTiles(numTiles);

        this.sinePosition = 0;
    }

    @Override
    public void update(double deltaTime) {
        this.rotate(rotationSpeed * deltaTime);
        sinePosition += sineSpeed * deltaTime;

        double sine = (Math.sin(sinePosition) + 1) / 2;
        double scale = sine * (maxScale - minScale) + minScale;
        this.setScale(scale, scale);
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        drawable.draw(graphics2D);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return new Pair<>(drawable.getSize().getFirst() * drawable.getNumTiles(), drawable.getSize().getSecond());
    }
}
