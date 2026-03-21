package src.escadasSerpentes.dto;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final int rows;
    private final int columns;
    private final Map<Integer, ISpecial> specials;

    public Board(int rows, int columns, Map<Integer, ISpecial> specials) {
        this.rows = rows;
        this.columns = columns;
        this.specials = specials;
    }

    public Map<Integer, ISpecial> getSpecials() {
        return Collections.unmodifiableMap(specials);
    }

    /**
     * Returns special in specified position or null if there are none.
     *
     * @param position The position to look at.
     * @return Special.
     */
    public ISpecial getSpecial(int position) {
        return specials.get(position);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getNumSpaces() {
        return rows * columns;
    }
}
