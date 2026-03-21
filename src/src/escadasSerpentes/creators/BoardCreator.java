package src.escadasSerpentes.creators;

import src.escadasSerpentes.providers.IResourceProvider;
import src.escadasSerpentes.Pair;
import src.escadasSerpentes.drawable.BoardDrawable;
import src.escadasSerpentes.drawable.BoardGridDrawable;
import src.escadasSerpentes.drawable.SpecialDrawable;
import src.escadasSerpentes.dto.Board;
import src.escadasSerpentes.dto.CardSpecial;
import src.escadasSerpentes.dto.ISpecial;
import src.escadasSerpentes.dto.LadderSpecial;
import src.escadasSerpentes.dto.SnakeSpecial;
import src.escadasSerpentes.framework.RandomProvider;

import java.util.*;

public class BoardCreator implements IBoardCreator {
    private static BoardCreator instance;

    private ISpecialDrawableCreator specialDrawableCreator;
    private ISpecialCreator specialCreator;
    private IResourceProvider resourceProvider;

    private final Random random;

    private BoardCreator() {
        random = RandomProvider.getRandom();
    }

    public void useSpecialCreator(ISpecialCreator specialCreator) {
        this.specialCreator = specialCreator;
    }

    public void useSpecialDrawableCreator(ISpecialDrawableCreator specialDrawableCreator) {
        this.specialDrawableCreator = specialDrawableCreator;
    }

    public void useResourceProvider(IResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public Pair<Board, BoardDrawable> create(int rows, int columns, float specialProbability) {
        Map<Integer, ISpecial> specials = new HashMap<>();
        Random random = RandomProvider.getRandom();
        int numSpaces = rows * columns;

        List<SpecialDrawable> specialDrawableList = new ArrayList<>();

        for (int i = 1; i < numSpaces - 1; i++) {
            // Tries to create special using probability.
            if (random.nextFloat() >= specialProbability)
                continue;

            Pair<ISpecial, SpecialDrawable> pair = createRandomSpecialDrawablePair(i, numSpaces);
            specials.put(i, pair.getFirst());
            specialDrawableList.add(pair.getSecond());
        }

        Board board = new Board(rows, columns, specials);
        BoardDrawable boardDrawable = new BoardDrawable(new BoardGridDrawable(board, resourceProvider), specialDrawableList);
        return new Pair<>(board, boardDrawable);
    }

    // Creates a new random special and drawable pair at the specified position on the board.
    private Pair<ISpecial, SpecialDrawable> createRandomSpecialDrawablePair(int position, int numBoardSpaces) {
        SpecialDrawable drawable;
        switch (random.nextInt(3)) {
            case 0 -> {
                LadderSpecial ladderSpecial = specialCreator.createLadderSpecial(position, numBoardSpaces);
                drawable = specialDrawableCreator.createLadderSpecialDrawable(ladderSpecial);
                return new Pair<>(ladderSpecial, drawable);
            }
            case 1 -> {
                SnakeSpecial snakeSpecial = specialCreator.createSnakeSpecial(position);
                drawable = specialDrawableCreator.createSnakeSpecialDrawable(snakeSpecial);
                return new Pair<>(snakeSpecial, drawable);
            }
            case 2 -> {
                CardSpecial cardSpecial = specialCreator.createCardSpecial(position);
                drawable = specialDrawableCreator.createCardSpecialDrawable(cardSpecial);
                return new Pair<>(cardSpecial, drawable);
            }
            default -> {
                return new Pair<>(null, null);
            }
        }
    }

    public static BoardCreator getInstance() {
        if (instance == null) {
            instance = new BoardCreator();
        }

        return instance;
    }
}
