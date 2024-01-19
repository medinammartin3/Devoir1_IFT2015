//Auteurs:
//Étienne Mitchell-Bouchard (20243430)
//Martin Medina (20235219)

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    static Stack<HiddenWord> hiddenWords = new Stack<>();

    public static void main(String[] args) {
        //Lit le contenu du fichier qui est pris en entrée et transforme celui-ci
        //en une ArrayList de mots cachés.

        String inputFilePath = "src/Input.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                int length = Integer.parseInt(splitLine[0]);
                int width = Integer.parseInt(splitLine[1]);
                char[][] grid = new char[length][width];
                for (int i = 0; i < length; ++i){
                    String row = reader.readLine();
                    String[] rowSplit = row.split(" ");
                    for(int j = 0; j < width; ++j){
                        grid[i][j] = rowSplit[j].charAt(0);
                    }
                }
                String[] words = reader.readLine().split(" ");
                Arrays.sort(words);
                hiddenWords.push(new HiddenWord(length, width, grid, words));
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        //Boucle qui résoud tous les mots cachés et qui imprime le résultat de
        //chaque résolution dans le format demandé.
        int i = 1;
        for(HiddenWord h : hiddenWords){
            System.out.println("Query " + i + ":");
            System.out.println(h.solve());
            ++i;
            //System.out.println("Average time for query in ms: " + doBenchmarkInMs(h));
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