package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombCount();
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

    int getTotalBombs() {
        return totalBombs;
    }

    private void fixBombCount() {
        int maxBombs = Ranges.getSquare() / 2;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    private void placeBomb() {
        while (true) {
            Coordinates coordinates = Ranges.getRandomCoordinates();
            if (Box.BOMB == bombMap.get(coordinates)) {
                continue;
            }
            bombMap.set(coordinates, Box.BOMB);
            incNumbersAroundBomb(coordinates);
            break;
        }
    }

    private void incNumbersAroundBomb(Coordinates coordinates) {
        for (Coordinates around : Ranges.getCoordinatesAround(coordinates)) {
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).nextNumberBox());
            }
        }
    }
}
