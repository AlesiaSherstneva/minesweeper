package sweeper;

public class Game {
    Matrix bombMap;

    public Game(int cols, int rows) {
        Ranges.setSize(new Coordinates(cols, rows));
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        bombMap.set(new Coordinates(0, 0), Box.BOMB);
    }

    public Box getBox(Coordinates coordinates) {
        return bombMap.get(coordinates);
    }
}
