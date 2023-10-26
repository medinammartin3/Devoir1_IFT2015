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
    private final Stack<String> savedPaths;
    private final Path tempPath;

    public HiddenWord(int length, int width, char[][] grid, String[] words){
        this.length = length;
        this.width = width;
        this.grid = grid;
        this.words = words;
        this.savedPaths = new Stack<>();
        this.tempPath = new Path();
    }
    //Fonction principale qui résout le mot caché et qui retourne tout les Paths sous formes
    //de String formatté pour print. Pour chacun des mots, elle parcourt la grille et lance
    //la fonction récursive findPositionOfNextLetter() lorsque la première lettre du mot est trouvée.
    public String solve() {
        for(String word : this.words){
            this.tempPath.setWord(word);
            char firstLetter = word.charAt(0);
            for(int i = 0; i < this.length; ++i) {
                for(int j = 0; j < this.width; ++j){
                    if (this.grid[i][j] == firstLetter) {
                        Point start = new Point(i, j);
                        this.tempPath.addPoint(start);
                        findPositionOfNextLetter();
                    }
                }
            }
        }
        return String.join("\n", this.savedPaths);
    }
    //Fonction qui trouve la position de la prochaine lettre du mot dans la grille récursivement.
    //Utilise l'attribut tempPath pour suivre la progression du mot et de la position dans la grille.
    //Le path est updaté pour que la récursion reste correcte, autant lorsqu'on s'enfonce que lorsqu'on remonte.
    //Ajoute les Paths trouvés sous forme de String dans l'attribut "savedPaths" et ne retourne rien.
    public void findPositionOfNextLetter(){
        if (this.tempPath.isComplete()){
            this.savedPaths.push(this.tempPath.toString());
            this.tempPath.removeLastPoint();
            return;
        }
        for(Point neighbor: getNeighborsOfPoint(this.tempPath.checkLastPoint())){
            if (this.grid[neighbor.getX()][neighbor.getY()] == this.tempPath.getNextLetter()){
                this.tempPath.addPoint(neighbor);
                findPositionOfNextLetter();
            }
        }
        this.tempPath.removeLastPoint();
    }
    //Prend un Point en paramètre et retourne tous ses voisins incluant le point même,
    //en tenant compte de la longueur et la largeur de la grille pour ne pas avoir des index
    //qui sont out of bounds. Retourne les voisins sous-forme de Point[] rempli.
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
