package sweeper;

public class Game {
    public Game (int cols, int rows) {
        Ranges.setSize(new Coordinates(cols, rows));
    }

    public Box getBox(Coordinates coordinates) {
        return Box.values() [(coordinates.x + coordinates.y) % Box.values().length];
    }
}
