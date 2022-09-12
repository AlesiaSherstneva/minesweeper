package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
    }

    public Box getBox(Coordinates coordinates) {
        if(Box.OPENED == flag.get(coordinates)) {
            return bomb.get(coordinates);
        }
        return flag.get(coordinates);
    }
}
