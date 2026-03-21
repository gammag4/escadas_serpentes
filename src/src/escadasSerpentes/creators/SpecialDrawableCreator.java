package src.escadasSerpentes.creators;

import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.providers.ILineSpecialProvider;
import src.escadasSerpentes.providers.LadderSpecialProvider;
import src.escadasSerpentes.providers.SnakeSpecialProvider;
import src.escadasSerpentes.drawable.CardDrawable;
import src.escadasSerpentes.drawable.CardSpecialDrawable;
import src.escadasSerpentes.drawable.LineSpecialDrawable;
import src.escadasSerpentes.dto.Card;
import src.escadasSerpentes.dto.CardSpecial;
import src.escadasSerpentes.dto.LadderSpecial;
import src.escadasSerpentes.dto.SnakeSpecial;

import java.awt.image.BufferedImage;

/**
 * Special drawable factory class.
 */
public class SpecialDrawableCreator implements ISpecialDrawableCreator {
    private static SpecialDrawableCreator instance;

    private IResourceProvider resourceProvider;

    private SpecialDrawableCreator() {

    }

    /**
     * Specifies what resource provider to use.
     *
     * @param resourceProvider The resource provider to use.
     */
    public void useResourceProvider(IResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public CardSpecialDrawable createCardSpecialDrawable(CardSpecial cardSpecial) {
        return new CardSpecialDrawable(cardSpecial, resourceProvider);
    }

    @Override
    public LineSpecialDrawable createLadderSpecialDrawable(LadderSpecial ladderSpecial) {
        BufferedImage middle = resourceProvider.getLadderMiddle();
        BufferedImage top = resourceProvider.getLadderTop();
        BufferedImage bottom = resourceProvider.getLadderBottom();

        ILineSpecialProvider ladderSpecialProvider = new LadderSpecialProvider(ladderSpecial, top, middle, bottom);
        return new LineSpecialDrawable(ladderSpecialProvider);
    }

    @Override
    public LineSpecialDrawable createSnakeSpecialDrawable(SnakeSpecial snakeSpecial) {
        BufferedImage middle = resourceProvider.getSnakeBody();
        BufferedImage top = resourceProvider.getSnakeHead();
        BufferedImage bottom = resourceProvider.getSnakeTail();

        ILineSpecialProvider snakeSpecialProvider = new SnakeSpecialProvider(snakeSpecial, top, middle, bottom);
        return new LineSpecialDrawable(snakeSpecialProvider);
    }

    /**
     * Returns singleton instance.
     *
     * @return Instance.
     */
    public static SpecialDrawableCreator getInstance() {
        if (instance == null) {
            instance = new SpecialDrawableCreator();
        }

        return instance;
    }
}
