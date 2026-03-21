package src.escadasSerpentes.other;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.providers.ILineTileProvider;
import src.escadasSerpentes.providers.LineTileProvider;
import src.escadasSerpentes.drawable.DrawableObject;
import src.escadasSerpentes.framework.IUpdatable;
import src.escadasSerpentes.framework.RandomProvider;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSpecialsDrawable extends DrawableObject implements IUpdatable {
    private final double minRotationSpeed = 0.1;
    private final double maxRotationSpeed = 38;
    private final double minSineSpeed = 0.08;
    private final double maxSineSpeed = 30;
    private final double minScale = 0.1;
    private final double maxScale = 2;
    private final int minTiles = 2;
    private final int maxTiles = 15;

    private final List<RandomSpecialDrawable> drawables;

    private final Random random;

    private Dimension size;

    public RandomSpecialsDrawable(IResourceProvider resourceProvider, int numberSpecials, int width, int height) {
        size = new Dimension();

        random = RandomProvider.getRandom();

        drawables = createDrawables(resourceProvider, numberSpecials, width, height);
    }

    private List<RandomSpecialDrawable> createDrawables(IResourceProvider resourceProvider, int numberSpecials, int width, int height) {
        List<RandomSpecialDrawable> drawables = new ArrayList<>();
        for (int i = 0; i < numberSpecials; i++) {
            ILineTileProvider provider = createRandomtileProvider(resourceProvider);
            RandomSpecialDrawable drawable = createRandomDrawable(provider, width, height);

            drawables.add(drawable);
        }

        return drawables;
    }

    private ILineTileProvider createRandomtileProvider(IResourceProvider resourceProvider) {
        BufferedImage top;
        BufferedImage middle;
        BufferedImage bottom;

        int tileType = random.nextInt(2);
        switch (tileType) {
            case 0 -> {
                top = resourceProvider.getSnakeHead();
                middle = resourceProvider.getSnakeBody();
                bottom = resourceProvider.getSnakeTail();
                return new LineTileProvider(top, middle, bottom);
            }
            case 1 -> {
                top = resourceProvider.getLadderTop();
                middle = resourceProvider.getLadderMiddle();
                bottom = resourceProvider.getLadderBottom();
                return new LineTileProvider(top, middle, bottom);
            }
        }

        return null;
    }

    private RandomSpecialDrawable createRandomDrawable(ILineTileProvider lineTileProvider, int width, int height) {
        double rotationSpeed = random.nextDouble() * (maxRotationSpeed - minRotationSpeed) + minRotationSpeed;
        double sineSpeed = random.nextDouble() * (maxSineSpeed - minSineSpeed) + minSineSpeed;
        int numTiles = random.nextInt(maxTiles - minTiles) + minTiles;
        int posX = random.nextInt(width);
        int posY = random.nextInt(height);

        double scale1 = random.nextDouble() * (maxScale - minScale) + minScale;
        double scale2 = random.nextDouble() * (maxScale - minScale) + minScale;
        double minScale = Math.min(scale1, scale2);
        double maxScale = Math.max(scale1, scale2);

        RandomSpecialDrawable drawable = new RandomSpecialDrawable(lineTileProvider, rotationSpeed, sineSpeed, numTiles, minScale, maxScale);
        drawable.setPosition(posX, posY);
        return drawable;
    }

    @Override
    public void update(double deltaTime) {
        for (RandomSpecialDrawable drawable : drawables) {
            drawable.update(deltaTime);
        }
    }

    @Override
    protected void drawObject(Graphics2D graphics2D) {
        for (RandomSpecialDrawable drawable : drawables) {
            drawable.drawCentered(graphics2D);
        }
    }

    @Override
    public Pair<Float, Float> getSize() {
        return new Pair<>((float) size.width, (float) size.height);
    }

    public void setSize(Dimension size) {
        this.size = size;
    }
}
