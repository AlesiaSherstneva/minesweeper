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
        bombMap.set(Ranges.getRandomCoordinates(), Box.BOMB);
    }
}
