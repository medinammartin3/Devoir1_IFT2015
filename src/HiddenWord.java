import java.util.ArrayList;

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
    public int getLength() {return this.length;}
    public int getWidth() {return this.width;}
    public char getCharAt(int x, int y) {return this.grid[x][y];}
    public String[] getWords() {return this.words;}

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
