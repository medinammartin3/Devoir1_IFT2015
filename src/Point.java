//Auteurs:
//Étienne Mitchell-Bouchard (20243430)
//Martin Medina (20235219)

//Simple classe qui permet de facilement faire interagir des coordonnées
//en 2D et qui a un pretty printer.
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

}
