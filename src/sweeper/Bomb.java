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
        incNumbersAroundBomb(coordinates);
    }

    private void incNumbersAroundBomb(Coordinates coordinates) {
        for (Coordinates around : Ranges.getCoordinatesAround(coordinates)) {
            if(Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).nextNumberBox());
            }
        }
    }
}
