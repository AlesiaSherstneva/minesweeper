package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        bombMap.set(new Coordinates(0, 0), Box.BOMB);
        bombMap.set(new Coordinates(0, 0), Box.BOMB);
        bombMap.set(new Coordinates(0, 1), Box.NUM1);
        bombMap.set(new Coordinates(1, 0), Box.NUM1);
        bombMap.set(new Coordinates(1, 1), Box.NUM1);
    }

    Box get(Coordinates coordinates) {
        return bombMap.get(coordinates);
    }
}
