package src.escadasSerpentes.creators;

import src.escadasSerpentes.dto.CardSpecial;
import src.escadasSerpentes.dto.LadderSpecial;
import src.escadasSerpentes.dto.SnakeSpecial;

/**
 * Interface for special factory.
 */
public interface ISpecialCreator extends ICardCreator {
    /**
     * Creates a new randomly generated ladder special.
     *
     * @param position       Ladder position (bottom).
     * @param numBoardSpaces Total number of spaces on the board.
     */
    LadderSpecial createLadderSpecial(int position, int numBoardSpaces);

    /**
     * Creates a new randomly generated snake special.
     *
     * @param position Snake position.
     */
    SnakeSpecial createSnakeSpecial(int position);

    /**
     * Creates a new card special.
     *
     * @param position Space where this special is.
     */
    CardSpecial createCardSpecial(int position);
}
