package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import dynamicBody.Player;
import game.*;
import game.levels.Level1;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

//Tracker for the player
public class Tracker implements StepListener {
    private final GameView view;
    private final Player player;

    private int invTime; //Time when turned invincible
    private boolean timeRecorded = false;
    private SoundClip hurt; //Hurt soundclip
    private GameLevel level;

    private static final BodyImage hit =
            new BodyImage("data/pHit.gif", 5.5f);
    private static final BodyImage idle =
            new BodyImage("data/pIdle.gif", 5.5f);

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
                fw.writeHighScore(player.getName(), Player.getCoinsCollected());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            player.setHasName(true);
        }
        if (player.getPosition().y < -5){
            player.setJumped(false); //If the players y coord is below -5, setJumped is false so they can double jump again
        }

        if (player.isInvincible()){
            try {
                hurt = new SoundClip("data/hurt.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException l) {
                System.out.println(l);
            }
            if (!timeRecorded){
                invTime = Level1.getSpawn(); //Gets the time when hit
                timeRecorded = true; //Won't update this time again until hit again
                hurt.play();
            }
            if (!Player.isShip()) {
                player.removeAllImages();
                player.addImage(hit);
            }
            //Resets everything so the player can be hit again
                if (Level1.getSpawn() - invTime == 20) {
                    timeRecorded = false;
                    player.setInvincible(false);
                    if (!Player.isShip()) {
                        player.removeAllImages();
                        player.addImage(idle);
                    }

                }
            }

        }



/*
        System.out.println(player.getPosition());
*/
        //System.out.println("poststep!");
    }

