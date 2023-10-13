import java.util.ArrayList;

public class Crossword {
    int Longueur;
    int Largeur;
    char[][] Grille;
    String[] Mots;

    public Crossword(int longueur, int largeur, char[][] grille, String[] mots){
        Longueur = longueur;
        Largeur = largeur;
        Grille = grille;
        Mots = mots;
    }

    public String Solve() {
        String total = "";
        for(String word : Mots){
            char firstLetter = word.charAt(0);
            for(int i = 0; i < Longueur; ++i) {
                for(int j = 0; j < Largeur; ++j){
                    if (Grille[i][j] == firstLetter) {
                        Point start = new Point(i, j);
                        String s = word + " " + start + FindPositionOfNextLetter(start, word, 1);
                        total += s + '\n';
                    }
                }
            }
        }
        return total;
    }
    public String FindPositionOfNextLetter(Point pt, String mot, int index){
        if (index == mot.length())
            return "";

        for(Point neighbor: getNeighborOfPoint(pt)){
            if (Grille[neighbor.X][neighbor.Y] == mot.charAt(index))
                return "->" + neighbor + FindPositionOfNextLetter(neighbor, mot, ++index);
        }
        return "";
    }
    public ArrayList<Point> getNeighborOfPoint(Point pt){
        ArrayList<Point> points = new ArrayList<>();
        int x = pt.X;
        int y = pt.Y;
        int nord = x == 0 ? x : x - 1;
        int sud = x == Longueur - 1 ? x : x + 1;
        int west = y == 0 ? y : y - 1;
        int est = y == Largeur - 1 ? y : y + 1;
        for(int i = nord; i <= sud; ++i) {
            for(int j = west; j <= est; ++j) {
                points.add(new Point(i, j));
            }
        }
        return points;
    }
}
