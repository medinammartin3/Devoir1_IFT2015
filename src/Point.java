import java.util.ArrayList;

public class Point {
    int X;
    int Y;
    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public String toString() {
        return "(" + X + "," + Y + ")";
    }

}
