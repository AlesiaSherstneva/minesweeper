package sweeper;

public class Game {
    Matrix bombMap;

    public Game(int cols, int rows) {
        Ranges.setSize(new Coordinates(cols, rows));
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
    }

    public Box getBox(Coordinates coordinates) {
        return bombMap.matrix[coordinates.x][coordinates.y];
    }
}
