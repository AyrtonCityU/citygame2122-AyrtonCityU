package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

class ScoreRecord{
    String name;
    int score;
    //potentially add level reached

    public ScoreRecord(String name, int score){
        this.name = name;
        this.score = score;
    }
}



public class HighScoreReader {

    private final String file;
    private final ArrayList<ScoreRecord> scores;
    public HighScoreReader(String file){
        this.file = file;
        scores = new ArrayList<>();
    }
    public void readScores() throws IOException {

        FileReader fr = null;
        BufferedReader reader = null;

        try {
            System.out.println("Reading " + file + " ...");

            fr = new FileReader(file);
            reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {

                String[] tokens = line.split(",");

                String name = tokens[0];

                int score = Integer.parseInt(tokens[1]);

                ScoreRecord scoreRecord = new ScoreRecord(name, score);
                scores.add(scoreRecord);

                line = reader.readLine();
            }
            System.out.println("...done.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
    public void displayScores(int top){
        System.out.println("Player\tScore");
        for (int i =0; i<scores.size() && i<top; i++){
            ScoreRecord score = scores.get(i);
            System.out.println(score.name + "\t" + score.score +"\t" );
        }

    }

    public void sortByScore(){
        scores.sort(new Comparator<ScoreRecord>() {
            @Override
            public int compare(ScoreRecord o1, ScoreRecord o2) {
                return o2.score - o1.score;
            }
        });
    }

    public static void main(String[] args) throws IOException {
        HighScoreReader demo = new HighScoreReader(args[0]);
        demo.readScores();
    }
}

