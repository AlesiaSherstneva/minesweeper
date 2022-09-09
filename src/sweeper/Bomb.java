package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    Box get(Coordinates coordinates) {
        return bombMap.get(coordinates);
    }

    private void placeBomb() {
        Coordinates coordinates = Ranges.getRandomCoordinates();
        bombMap.set(coordinates, Box.BOMB);
        for (Coordinates around : Ranges.getCoordinatesAround(coordinates)) {
            bombMap.set(around, Box.NUM1);
        }
    }
}
