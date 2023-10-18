//Auteurs:
//Étienne Mitchell-Bouchard (20243430)
//Martin Medina (20235219)

import java.util.ArrayList;

//Classe qui contient toutes les informations nécessaires à la résolution d'un mot caché
//sous formes d'attributs. Elle contient aussi les méthodes nécessaires pour résoudre le mot caché.
public class HiddenWord {
    private final int length;
    private final int width;
    private final char[][] grid;
    private final String[] words;
    private final ArrayList<Path> paths;

    public HiddenWord(int length, int width, char[][] grid, String[] words){
        this.length = length;
        this.width = width;
        this.grid = grid;
        this.words = words;
        this.paths = new ArrayList<>();
    }
    //Fonction principale qui résoud le mot caché et qui retourne tout les Paths sous formes
    //de String formatté pour print. Pour chacun des mots, elle parcours la grille et lance
    //la fonction récursive findPositionOfNextLetter() lorsque la première lettre du mot est trouvé.
    public String solve() {
        for(String word : this.words){
            char firstLetter = word.charAt(0);
            for(int i = 0; i < this.length; ++i) {
                for(int j = 0; j < this.width; ++j){
                    if (this.grid[i][j] == firstLetter) {
                        Point start = new Point(i, j);
                        Path path = new Path(word);
                        path.addPoint(start);
                        findPositionOfNextLetter(path);
                    }
                }
            }
        }
        ArrayList<String> pathsToOutput = new ArrayList<>();
        for(Path path : paths){
            pathsToOutput.add(path.toString());
        }
        return String.join("\n", pathsToOutput);
    }
    //Prend un Path en paramètre et trouve la position de la prochaine lettre dans le mot récursivement.
    //Utilise "path" pour savoir à quelle lettre du mot et position dans la grille nous sommes rendus.
    //Le path est updaté pour que la récursion reste correct, autant lorsqu'on s'enfonce que lorsqu'on remonte.
    //Ajoute les Path trouvés dans l'attribut "paths" et ne retourne rien.
    public void findPositionOfNextLetter(Path path){
        if (path.getIndex() == path.getWordLength()){
            paths.add(path.copy());
            path.removeLastPoint();
            return;
        }
        Point current = path.checkLastPoint();
        char letter = path.getNextLetter();
        for(Point neighbor: getNeighborsOfPoint(current)){
            if (this.grid[neighbor.getX()][neighbor.getY()] == letter){
                path.addPoint(neighbor);
                findPositionOfNextLetter(path);
            }
        }
        path.removeLastPoint();
    }
    //Prend un Point en paramètre et retourne tout ses voisins incluant le point-même,
    //en tenant compte de la longueur et la largeur de la grille pour ne pas avoir des index
    //qui sont out of bounds. Retourne les voisins sous-forme de ArrayList<Point>.
    public ArrayList<Point> getNeighborsOfPoint(Point pt){
        ArrayList<Point> points = new ArrayList<>();
        int x = pt.getX();
        int y = pt.getY();
        int nord = x == 0 ? x : x - 1;
        int sud = x == this.length - 1 ? x : x + 1;
        int west = y == 0 ? y : y - 1;
        int est = y == this.width - 1 ? y : y + 1;
        for(int i = nord; i <= sud; ++i) {
            for(int j = west; j <= est; ++j) {
                points.add(new Point(i, j));
            }
        }
        return points;
    }
}
