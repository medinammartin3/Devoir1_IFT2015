//Auteurs:
//Étienne Mitchell-Bouchard (20243430)
//Martin Medina (20235219)

import java.util.Stack;

//Classe qui permet de sauvegarder les chemins trouvés et gérer
//la recherche récursive à l'aide d'un Stack.
public class Path {
    private final Stack<Point> pointPath;
    private String word;

    public Path(){
        this.word = "";
        this.pointPath = new Stack<>();
    }
    //Crée une copie du Path actuel ailleurs en mémoire pour le sauvegarder.
    public Path copy(){
        Path newPath = new Path();
        newPath.setWord(this.word);
        for(Point p : this.pointPath){
            newPath.addPoint(p);
        }
        return newPath;
    }
    //Pretty printer pour respecter le format demandé.
    public String toString() {
        Stack<String> pts = new Stack<>();
        for(Point pt : this.pointPath){
            pts.push(pt.toString());
        }
        return this.word + " " + String.join("->", pts);
    }
    public void addPoint(Point pt){ this.pointPath.push(pt); }
    public Point checkLastPoint(){ return this.pointPath.peek(); }
    public void removeLastPoint() { this.pointPath.pop(); }
    public char getNextLetter(){
        return this.word.charAt(this.pointPath.size());
    }
    public boolean isComplete() {
        return this.pointPath.size() == this.word.length();
    }
    public void setWord(String word) {this.word = word;}
}
