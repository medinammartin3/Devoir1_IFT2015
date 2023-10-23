//Auteurs:
//Étienne Mitchell-Bouchard (20243430)
//Martin Medina (20235219)

import java.util.Stack;

//Classe qui contient toutes les informations nécessaires à la résolution d'un mot caché
//sous formes d'attributs. Elle contient aussi les méthodes nécessaires pour résoudre le mot caché.
public class HiddenWord {
    private final int length;
    private final int width;
    private final char[][] grid;
    private final String[] words;
    private final Stack<Path> paths;
    private final Path tempPath;

    public HiddenWord(int length, int width, char[][] grid, String[] words){
        this.length = length;
        this.width = width;
        this.grid = grid;
        this.words = words;
        this.paths = new Stack<>();
        this.tempPath = new Path();
    }
    //Fonction principale qui résoud le mot caché et qui retourne tout les Paths sous formes
    //de String formatté pour print. Pour chacun des mots, elle parcours la grille et lance
    //la fonction récursive findPositionOfNextLetter() lorsque la première lettre du mot est trouvé.
    public String solve() {
        for(String word : this.words){
            tempPath.setWord(word);
            char firstLetter = word.charAt(0);
            for(int i = 0; i < this.length; ++i) {
                for(int j = 0; j < this.width; ++j){
                    if (this.grid[i][j] == firstLetter) {
                        Point start = new Point(i, j);
                        tempPath.addPoint(start);
                        findPositionOfNextLetter();
                    }
                }
            }
        }
        Stack<String> pathsToOutput = new Stack<>();
        for(Path path : this.paths){
            pathsToOutput.push(path.toString());
        }
        return String.join("\n", pathsToOutput);
    }
    //Utilise le Path global de la classe et trouve la position de la prochaine lettre dans le mot récursivement.
    //Utilise "path" pour savoir à quelle lettre du mot et quelle position dans la grille nous sommes rendus.
    //Le path est updaté pour que la récursion reste correcte, autant lorsqu'on s'enfonce que lorsqu'on remonte.
    //Ajoute les Path trouvés dans l'attribut "paths" et ne retourne rien.

    public void findPositionOfNextLetter(){
        if (tempPath.isComplete()){
            this.paths.push(tempPath.copy());
            tempPath.removeLastPoint();
            return;
        }
        for(Point neighbor: getNeighborsOfPoint(tempPath.checkLastPoint())){
            if (this.grid[neighbor.getX()][neighbor.getY()] == tempPath.getNextLetter()){
                tempPath.addPoint(neighbor);
                findPositionOfNextLetter();
            }
        }
        tempPath.removeLastPoint();
    }
    //Prend un Point en paramètre et retourne tous ses voisins incluant le point même,
    //en tenant compte de la longueur et la largeur de la grille pour ne pas avoir des index
    //qui sont out of bounds. Retourne les voisins sous-forme de array de Point rempli.
    public Point[] getNeighborsOfPoint(Point pt){
        int x = pt.getX();
        int y = pt.getY();
        int nord = x == 0 ? x : x - 1;
        int sud = x == this.length - 1 ? x : x + 1;
        int west = y == 0 ? y : y - 1;
        int est = y == this.width - 1 ? y : y + 1;
        int nbPoints = 9;
        boolean xIsBorder = x == nord || x == sud;
        boolean yIsBorder = y == west || y == est;
        if (xIsBorder ^ yIsBorder)
            nbPoints = 6;
        if (xIsBorder && yIsBorder)
            nbPoints = 4;
        Point[] points = new Point[nbPoints];
        int index = 0;
        for(int i = nord; i <= sud; ++i) {
            for(int j = west; j <= est; ++j) {
                points[index] = new Point(i, j);
                ++index;
            }
        }
        return points;
    }
}
