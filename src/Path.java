import java.util.Stack;
import java.util.ArrayList;
public class Path {
    private final Stack<Point> pointPath;
    private final String word;
    private int index;

    public Path(String word){
        this.word = word;
        this.pointPath = new Stack<>();
        this.index = 0;
    }
    public Path copy(){
        Path newPath = new Path(this.word);
        for(Point p : this.pointPath){
            newPath.addPoint(p);
        }
        return newPath;
    }

    public String toString() {
        ArrayList<String> pts = new ArrayList<>();
        for(Point pt : this.pointPath){
            pts.add(pt.toString());
        }
        return this.word + " " + String.join("->", pts);
    }

    public void addPoint(Point pt){
        ++this.index;
        this.pointPath.add(pt);
    }

    public Point checkLastPoint(){
        return this.pointPath.peek();
    }

    public void removeLastPoint(){
        --this.index;
        this.pointPath.pop();
    }
    public char getNextLetter(){
        return this.word.charAt(this.index);
    }
    public int getIndex(){ return this.index;}
    public int getWordLength() {return this.word.length();}
}
