import java.awt.image.AreaAveragingScaleFilter;
import java.util.Stack;
import java.util.ArrayList;
public class Path {
    Stack<Point> pointPath;
    String Word;

    public Path(String word){
        Word = word;
        pointPath = new Stack<>();
    }

    public String toString() {
        ArrayList<String> pts = new ArrayList<>();
        for(Point pt : pointPath){
            pts.add(pt.toString());
        }
        return Word + " " + String.join("->", pts);
    }

    public void addPoint(Point pt){
        pointPath.add(pt);
    }

    public Point peek(){
        return pointPath.peek();
    }

    public Point pop(){
        return pointPath.pop();
    }
}
