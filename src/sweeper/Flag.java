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

    void setClosedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Box.CLOSED);
    }

    void toggleFlaggedToBox(Coordinates coordinates) {
        switch (flagMap.get(coordinates)) {
            case FLAGGED:
                setClosedToBox(coordinates);
                break;
            case CLOSED:
                setFlaggedToBox(coordinates);
                break;
        }

    }
}
