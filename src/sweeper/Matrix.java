package sweeper;

class Matrix {
    Box[][] matrix;

    Matrix(Box box) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coordinates coordinates : Ranges.getAllCoordinates()) {
            matrix[coordinates.x][coordinates.y] = box;
        }
    }
}
