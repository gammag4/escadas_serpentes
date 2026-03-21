package src.escadasSerpentes.creators;

import src.escadasSerpentes.IUpdateCardListener;
import src.escadasSerpentes.dto.*;
import src.escadasSerpentes.framework.RandomProvider;

import java.util.Random;

/**
 * Special factory class.
 */
public class SpecialCreator implements ISpecialCreator {
    private static SpecialCreator instance;

    private IUpdateCardListener createCardListener;

    private final Random random;

    private final int minLadderSpaces = 1;
    private final int maxLadderSpaces = 20;
    private final int minSnakeSpaces = 1;
    private final int maxSnakeSpaces = 20;
    private final int minCardSpaces = 1;
    private final int maxCardSpaces = 3;

    /**
     * Creates a new special creator.
     */
    private SpecialCreator() {
        random = RandomProvider.getRandom();
    }

    /**
     * Sets the object that listens for card creation events.
     *
     * @param createCardListener The listener.
     */
    public void useCreateCardListener(IUpdateCardListener createCardListener) {
        this.createCardListener = createCardListener;
    }

    @Override
    public LadderSpecial createLadderSpecial(int position, int numBoardSpaces) {
        int maxSpaces = Math.min(position + maxLadderSpaces, numBoardSpaces - 1) - position;
        int ladderSize = random.nextInt(maxSpaces - minLadderSpaces + 1) + minLadderSpaces;

        return new LadderSpecial(position, position + ladderSize);
    }

    @Override
    public SnakeSpecial createSnakeSpecial(int position) {
        int maxSpaces = Math.min(maxSnakeSpaces, position);
        int snakeSize = random.nextInt(maxSpaces - minSnakeSpaces + 1) + minSnakeSpaces;

        return new SnakeSpecial(position, position - snakeSize);
    }

    @Override
    public CardSpecial createCardSpecial(int position) {
        return new CardSpecial(position, this, createCardListener);
    }

    @Override
    public Card createCard() {
        boolean goForward = random.nextBoolean();
        int numSpaces = random.nextInt(maxCardSpaces - minCardSpaces + 1) + minCardSpaces;

        return new Card(goForward, numSpaces);
    }

    /**
     * Returns singleton instance.
     *
     * @return Instance.
     */
    public static SpecialCreator getInstance() {
        if (instance == null) {
            instance = new SpecialCreator();
        }

        return instance;
    }
}
