package game;

import city.cs.engine.*;
import dynamicBody.Player;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.Level4;
import listeners.mkb.DirectionalShooting;
import listeners.mkb.PlayerController;
import listeners.step.Tracker;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.JFrame;

/**Class to run the game*/
public class Game {

    private static GameLevel level;
    private static boolean plot;
    private static boolean controls;
    /**gameMusic that is changed throughout the game*/
    public static SoundClip gameMusic;
    /**Pause variable*/
    public static int pause = 1;
    /**pscore is used to save the players score at the end of each elvel*/
    public int pscore = 0;
    /**GameView object to*/
    public static GameView view;
    private Game game;
    private final PlayerController controller;
    private final DirectionalShooting mouseController;
    private boolean gameOver;
    /**The initial level background is for level 1*/
    public static int levelBackground = 1;
    /**The initial level song is for level 1*/
    public static int levelSong = 1;
    private boolean menuVisible;
    private final ControlPanel controlPanel;
    /**JFrame which is used to view the game*/
    public static JFrame frame;

    /**Gets the background for current level*/
    public static int getLevelBackground() {
        return levelBackground;
    }
    /**Sets the background for current level*/
    public void setLevelBackground(int levelBackground) {
        Game.levelBackground = levelBackground;
    }

    /**Checks if the game is currently in plot mode*/
    public static boolean isPlot() {
        return plot;
    }
    /**Sets the game to plot (so the plot can be shown)*/
    public void setPlot(boolean plot) {
        Game.plot = plot;
    }

    /**Checks if the game is currently in controls mode*/
    public static boolean isControls() {
        return controls;
    }
    /**Sets the game to controls (so the controls can be shown)*/
    public void setControls(boolean controls) {
        Game.controls = controls;
    }

    /**Gets the current game level */
    public static GameLevel getLevel() {
        return level;
    }
    /**Sets the current game level*/
    public void setLevel(GameLevel level) {
        Game.level = level;
    }

    /** Initialise a new Game. */
    public Game() {

        gameOver = false;
        menuVisible = false;

        level = new Level1(this);
        Player.setShip(false);
        if (levelSong == 1) {
            try {
                gameMusic = new SoundClip("data/lev1bg.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }

        //make a view to look into the game world
        view = new GameView(level, 1100, 550); //1100 550

        //optional: draw a 1-metre grid over the view
       // view.setGridResolution(1);



        //create a Java window (frame) and add the game
        //   view to it
        frame = new JFrame("Clustertruck 2D");
        frame.add(view);
        view.setFocusable(true);

        controlPanel = new ControlPanel(this, view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        controller = new PlayerController(GameLevel.getPlayer(), this);
        view.addKeyListener(controller);

        mouseController = new DirectionalShooting(GameLevel.getPlayer(), view);
        view.addMouseListener(mouseController);

        StepListener tracker = new Tracker(view, GameLevel.getPlayer());
        level.addStepListener(tracker);

        //Start the game!
        level.start();



    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
        //System.out.println(startTime);


    }

    /**Checks if there's a game over*/
    public boolean isGameOver() {
        return gameOver;
    }
    /**Sets the game to game over mode*/
    public void setGameOver(boolean over){
        gameOver = over;
        level.stop();
        }
    /**Toggles the game menu*/
    public void toggleMenu(){
        if(menuVisible) {
            //hide menu
            frame.remove(controlPanel.mainPanel);
            menuVisible = false;
            frame.pack();
            level.start();
/*
            gameMusic1.resume();
*/
            view.setFocusable(true);
        }
        else{
            //show menu
            frame.add(controlPanel.mainPanel, BorderLayout.WEST);
            menuVisible = true;
            frame.pack();
            level.stop();
            /*gameMusic1.stop();
            gameMusic2.stop();
            gameMusic3.stop();*/
        }
    }

    /**Method to go to the next level*/
    public void goToNextLevel(){
        if (level instanceof Level1){
            pscore = Player.getCoinsCollected();
            level.stop();
            level = new Level2(this);
            view.setWorld(level);

            controller.updatePlayer(GameLevel.getPlayer());
            mouseController.updatePlayer(GameLevel.getPlayer());

            level.start();
            setLevelBackground(2);
            levelSong = 2;
            Player.setCoins(pscore);
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/lev2bgm.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }
        else if (level instanceof Level2){
            pscore = Player.getCoinsCollected();
            level.stop();
            level = new Level3(this);
            view.setWorld(level);

            controller.updatePlayer(GameLevel.getPlayer());
            mouseController.updatePlayer(GameLevel.getPlayer());

            level.start();
            setLevelBackground(3);
            levelSong = 3;
            gameMusic.stop();
            Player.setCoins(pscore);
            try {
                gameMusic = new SoundClip("data/lev3bgm.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }

        }
        else if (level instanceof Level3){
            pscore = Player.getCoinsCollected();
            level.stop();
            Player.setShip(true);
            level = new Level4(this);
            view.setWorld(level);

            controller.updatePlayer(GameLevel.getPlayer());
            mouseController.updatePlayer(GameLevel.getPlayer());

            level.start();
            setLevelBackground(4);
            levelSong = 4;
            gameMusic.stop();
            Player.setCoins(pscore);
            try {
                gameMusic = new SoundClip("data/lev4bgm.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }
        else if(level instanceof Level4){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter username");
            String userName = myObj.nextLine();  // Read user input
            GameLevel.getPlayer().setName(userName);
            System.out.println("Username is: " + userName);  // Output user input
            HighScoreWriter fw = new HighScoreWriter("data/HiScores.txt");
            try {
                fw.writeHighScore(GameLevel.getPlayer().getName(), Player.getCoinsCollected());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            GameLevel.getPlayer().setHasName(true);
            System.out.println("Well done! Game complete.");
            System.exit(0);
        }
        if (gameOver){
            level.stop();
        }
    }

}


