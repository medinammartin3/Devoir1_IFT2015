//Auteurs:
//Étienne Mitchell-Bouchard (20243430)
//Martin Medina (20235219)

public class Point {
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

}
