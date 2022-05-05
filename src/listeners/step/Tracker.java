package listeners.step;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import dynamicBody.Player;
import game.Game;
import game.GameView;
import game.HighScoreWriter;
import game.NameInput;

import java.io.IOException;
import java.util.Scanner;

public class Tracker implements StepListener {
    private final GameView view;
    private final Player player;
    public Tracker(GameView view, Player player) {
        this.view = view;
        this.player = player;
    }
    public void preStep(StepEvent e) {}
    public void postStep(StepEvent e) {
        if (player.getPlayerHealth()==0 && !player.isHasName()){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter username");
            String userName = myObj.nextLine();  // Read user input
            player.setName(userName);
            System.out.println("Username is: " + userName);  // Output user input
            HighScoreWriter fw = new HighScoreWriter("data/HiScores.txt");
            try {
                fw.writeHighScore(player.getName(), player.getCoinsCollected());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            player.setHasName(true);
        }
        if (player.getPosition().y < -5){
            player.setJumped(false);
        }

        System.out.println(player.getPosition());
        //System.out.println("poststep!");
    }

}