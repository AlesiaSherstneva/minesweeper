package sweeper;

class Flag {
    private Matrix flagMap;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
    }

    Box get(Coordinates coordinates) {
        return flagMap.get(coordinates);
    }

    void setOpenedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Box.OPENED);
    }

    void setFlaggedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Box.FLAGGED);
    }
}
