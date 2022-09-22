package sweeper;

class Flag {
    private Matrix flagMap;

    private int totalFlagged;
    private int totalClosed;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalFlagged = 0;
        totalClosed = Ranges.getSquare();
    }

    Box get(Coordinates coordinates) {
        return flagMap.get(coordinates);
    }

    void setOpenedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Box.OPENED);
        totalClosed--;
    }

    int getTotalFlagged() {
        return totalFlagged;
    }

    int getTotalClosed() {
        return totalClosed;
    }

    void setFlaggedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Box.FLAGGED);
        totalFlagged++;
    }

    void setClosedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Box.CLOSED);
        totalFlagged--;
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

    public void setFlaggedToLastClosedBoxes() {
        for(Coordinates coordinates: Ranges.getAllCoordinates()) {
            if(Box.CLOSED == flagMap.get(coordinates)) {
                setFlaggedToBox(coordinates);
            }
        }
    }
}
