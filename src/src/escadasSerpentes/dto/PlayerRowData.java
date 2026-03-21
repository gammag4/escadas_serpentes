package src.escadasSerpentes.dto;

public class PlayerRowData {
    private final int id;
    private final String name;

    public PlayerRowData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }
}
