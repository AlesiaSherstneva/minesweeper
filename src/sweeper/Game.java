package sweeper;

public class Game {
    Bomb bomb;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
    }

    public void start() {
        bomb.start();
    }

    public Box getBox(Coordinates coordinates) {
        return bomb.get(coordinates);
    }
}
