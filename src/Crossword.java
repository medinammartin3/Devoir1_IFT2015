import java.util.ArrayList;

public class Crossword {
    int Longueur;
    int Largeur;
    char[][] Grille;
    String[] Mots;
    ArrayList<Path> paths;

    public Crossword(int longueur, int largeur, char[][] grille, String[] mots){
        Longueur = longueur;
        Largeur = largeur;
        Grille = grille;
        Mots = mots;
        paths = new ArrayList<>();
    }

    public void pathsForWord (String word){
        char firstLetter = word.charAt(0);
        for(int i = 0; i < Longueur; ++i) {
            for(int j = 0; j < Largeur; ++j){
                if (Grille[i][j] == firstLetter) {
                    Point start = new Point(i, j);
                    Path path = new Path(word);
                    path.addPoint(start);
                    FindPositionOfNextLetter(path, 1);
                }
            }
        }
    }

    public String Solve() {
        for(String word : Mots){
            pathsForWord(word);
        }
        ArrayList<String> pts = new ArrayList<>();
        for(Path path : paths){
            pts.add(path.toString());
        }
        return String.join("\n", pts);
    }
    public void FindPositionOfNextLetter(Path path, int index){
        if (index == path.Word.length()){
            paths.add(path);
            path.pop();
            --index;
            return;
        }
        for(Point neighbor: getNeighborOfPoint(path.peek())){
            if (Grille[neighbor.X][neighbor.Y] == path.Word.charAt(index)){
                ++index;
                path.addPoint(neighbor);
                FindPositionOfNextLetter(path, index);

            }
        }

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
