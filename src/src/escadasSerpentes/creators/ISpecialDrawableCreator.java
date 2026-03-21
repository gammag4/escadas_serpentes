package src.escadasSerpentes.creators;

import src.escadasSerpentes.drawable.CardSpecialDrawable;
import src.escadasSerpentes.drawable.LineSpecialDrawable;
import src.escadasSerpentes.dto.CardSpecial;
import src.escadasSerpentes.dto.LadderSpecial;
import src.escadasSerpentes.dto.SnakeSpecial;

/**
 * Interface for special drawable factory.
 */
public interface ISpecialDrawableCreator {
    /**
     * Creates a drawable to draw the specified card special.
     *
     * @param cardSpecial The card special to create the drawable from.
     */
    CardSpecialDrawable createCardSpecialDrawable(CardSpecial cardSpecial);

    /**
     * Creates a drawable to draw the specified ladder special.
     *
     * @param ladderSpecial The ladder special to create the drawable from.
     */
    LineSpecialDrawable createLadderSpecialDrawable(LadderSpecial ladderSpecial);

    /**
     * Creates a drawable to draw the specified snake special.
     *
     * @param snakeSpecial The snake special to create the drawable from.
     */
    LineSpecialDrawable createSnakeSpecialDrawable(SnakeSpecial snakeSpecial);
}
