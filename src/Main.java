//Auteurs:
//Étienne Mitchell-Bouchard (20243430)
//Martin Medina (20235219)

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<HiddenWord> hiddenWords = new ArrayList<>();
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
                hiddenWords.add(new HiddenWord(longueur, largeur, grille, mots));
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        int i = 1;
        for(HiddenWord h : hiddenWords){
            System.out.println("Query " + i + ":");
            System.out.println(h.solve());
            //System.out.println("Average time for query in ms: " + doBenchmarkInMs(h));
            ++i;
        }
    }
//    public static long doBenchmarkInMs(HiddenWord h){
//        int nbOfTimes = 10;
//        long startTime = System.nanoTime();
//        for(short n = 0; n < nbOfTimes; ++n)
//            h.solve();
//        long time = (System.nanoTime() - startTime) / nbOfTimes;
//        return time / 1000000;
//    }
}