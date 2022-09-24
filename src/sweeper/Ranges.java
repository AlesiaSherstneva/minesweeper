package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coordinates size;
    private static ArrayList<Coordinates> allCoordinates;
    private static final Random random = new Random();

    static void setSize(Coordinates size) {
        Ranges.size = size;
        allCoordinates = new ArrayList<>();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                allCoordinates.add(new Coordinates(x, y));
            }
        }
    }

    public static Coordinates getSize() {
        return size;
    }

    public static ArrayList<Coordinates> getAllCoordinates() {
        return allCoordinates;
    }

    static boolean inRange(Coordinates coordinates) {
        return coordinates.x >= 0 && coordinates.x < size.x &&
                coordinates.y >= 0 && coordinates.y < size.y;
    }

    static Coordinates getRandomCoordinates() {
        return new Coordinates(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coordinates> getCoordinatesAround(Coordinates coordinates) {
        Coordinates around;
        ArrayList<Coordinates> listAround = new ArrayList<>();
        for (int x = coordinates.x - 1; x <= coordinates.x + 1; x++) {
            for (int y = coordinates.y - 1; y <= coordinates.y + 1; y++) {
                around = new Coordinates(x, y);
                if (inRange(around)) {
                    if (!around.equals(coordinates)) {
                        listAround.add(around);
                    }
                }
            }
        }
        return listAround;
    }

    static int getSquare() {
        return size.x * size.y;
    }
}
