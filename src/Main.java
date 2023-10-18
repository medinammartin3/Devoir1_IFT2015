import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Crossword> crosswords = new ArrayList<Crossword>();
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                int longueur = Integer.parseInt(splitLine[0]);
                int largeur = Integer.parseInt(splitLine[1]);
                char[][] grille = new char[longueur][largeur];
                for (int i = 0; i < longueur; ++i){
                    String row = reader.readLine();
                    String[] rowSplit = row.split(" ");
                    for(int j = 0; j < largeur; ++j){
                        grille[i][j] = rowSplit[j].charAt(0);
                    }
                }
                String[] mots = reader.readLine().split(" ");
                Arrays.sort(mots);
                crosswords.add(new Crossword(longueur, largeur, grille, mots));
            }

        } catch(IOException e) {
            System.out.println(e);
        }
        for(Crossword c : crosswords){
            for(int i = 0; i < c.Longueur; ++i){
                for(int j = 0; j < c.Largeur; ++j)
                    System.out.print(c.Grille[i][j] + " ");
                System.out.println();
            }
            for(String word : c.Mots)
                System.out.print(word + " ");
            System.out.println();
        }
        System.out.println();
        int i = 1;
        for(Crossword c : crosswords){
            System.out.println("Query " + i + ":");
            System.out.println(c.Solve());
            ++i;
        }
        Path yo = new Path("yo");
        for(int test = 0; test < 5; ++test){
            yo.addPoint(new Point(test, test));
        }
        System.out.println(yo);

    }
}