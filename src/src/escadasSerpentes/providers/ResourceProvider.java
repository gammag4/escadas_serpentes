package src.escadasSerpentes.providers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceProvider implements IResourceProvider {
    private static ResourceProvider instance;

    private final String resourcesPath;

    private Font font;

    private BufferedImage cardIcon;
    private BufferedImage cardLayout;

    private BufferedImage dieIcon;
    private BufferedImage dieLayout;

    private BufferedImage ladderTop;
    private BufferedImage ladderMiddle;
    private BufferedImage ladderBottom;

    private BufferedImage snakeHead;
    private BufferedImage snakeBody;
    private BufferedImage snakeTail;

    private BufferedImage pawnBlue;
    private BufferedImage pawnGreen;
    private BufferedImage pawnRed;
    private BufferedImage pawnYellow;

    // Loads resources.
    private ResourceProvider() {
        resourcesPath = "./assets/";

        try {
            load();
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load() throws IOException, FontFormatException {
        font = loadFont("font.ttf");

        cardIcon = loadImage("card/cardIcon.png", 2.5);
        cardLayout = loadImage("card/cardLayout.png");

        dieIcon = loadImage("die/dieIcon.png");
        dieLayout = loadImage("die/dieLayout.png");

        ladderTop = loadImage("ladder/ladderTop.png", 1.5);
        ladderMiddle = loadImage("ladder/ladderMiddle.png", 1.5);
        ladderBottom = loadImage("ladder/ladderBottom.png", 1.5);

        snakeHead = loadImage("snake/snakeHead.png", 1.5);
        snakeBody = loadImage("snake/snakeBody.png", 1.5);
        snakeTail = loadImage("snake/snakeTail.png", 1.5);

        pawnBlue = loadImage("pawns/pawnBlue.png");
        pawnRed = loadImage("pawns/pawnRed.png");
        pawnGreen = loadImage("pawns/pawnGreen.png");
        pawnYellow = loadImage("pawns/pawnYellow.png");
    }

    private Font loadFont(String path) throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, loadFile(path));
        font = font.deriveFont(16f);
        return font;
    }

    private BufferedImage loadImage(String path, double scale) throws IOException {
        BufferedImage image = loadImage(path);
        AffineTransform tx = new AffineTransform();
        tx.setToScale(scale, scale);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    private BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(loadFile(path));
    }

    private File loadFile(String path) {
        return new File(resourcesPath + path);
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public Color getTextColor() {
        return Color.black;
    }

    @Override
    public Color getOutlinedTextColor() {
        return Color.white;
    }

    @Override
    public Color getOutlinedTextOutlineColor() {
        return Color.black;
    }

    @Override
    public Color getBoardOutlineColor() {
        return Color.black;
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(255, 222, 130);
    }

    @Override
    public BufferedImage getCardIcon() {
        return cardIcon;
    }

    @Override
    public BufferedImage getCardLayout() {
        return cardLayout;
    }

    @Override
    public BufferedImage getDieIcon() {
        return dieIcon;
    }

    @Override
    public BufferedImage getDieLayout() {
        return dieLayout;
    }

    @Override
    public BufferedImage getLadderTop() {
        return ladderTop;
    }

    @Override
    public BufferedImage getLadderMiddle() {
        return ladderMiddle;
    }

    @Override
    public BufferedImage getLadderBottom() {
        return ladderBottom;
    }

    @Override
    public BufferedImage getSnakeHead() {
        return snakeHead;
    }

    @Override
    public BufferedImage getSnakeBody() {
        return snakeBody;
    }

    @Override
    public BufferedImage getSnakeTail() {
        return snakeTail;
    }

    @Override
    public BufferedImage getPawnBlue() {
        return pawnBlue;
    }

    @Override
    public BufferedImage getPawnGreen() {
        return pawnGreen;
    }

    @Override
    public BufferedImage getPawnRed() {
        return pawnRed;
    }

    @Override
    public BufferedImage getPawnYellow() {
        return pawnYellow;
    }

    public static ResourceProvider getInstance() {
        if (instance == null) {
            instance = new ResourceProvider();
        }

        return instance;
    }
}
