package src.escadasSerpentes.creators;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.drawable.BoardDrawable;
import src.escadasSerpentes.dto.Board;

public interface IBoardCreator {
    Pair<Board, BoardDrawable> create(int rows, int columns, float specialProbability);
}
