package game;

import city.cs.engine.*;
import dynamicBody.Player;
import listeners.mkb.DirectionalShooting;
import listeners.mkb.PlayerController;
import listeners.step.EnemyTracker;
import listeners.step.Tracker;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.JFrame;

public class Game {

    public SoundClip gameMusic1;
    public SoundClip gameMusic2;
    public SoundClip gameMusic3;
    public SoundClip gameMusic4;
    public static int pause = 1;
    public static void setPause() {
        pause = 1;
    }

    public int getPause() {
        return pause;
    }

    private GameLevel level;
    private final GameView view;
    private Game game;
    private final PlayerController controller;
    private final DirectionalShooting mouseController;
    private boolean gameOver;

    public static int getLevelBackground() {
        return levelBackground;
    }

    public void setLevelBackground(int levelBackground) {
        Game.levelBackground = levelBackground;
    }

    public static int levelBackground = 1;
    public static int levelSong = 1;

    private boolean menuVisible;
    private final ControlPanel controlPanel;
    private final JFrame frame;



    /** Initialise a new Game. */
    public Game() {

        gameOver = false;
        menuVisible = false;

        level = new Level1(this);
        Player.setShip(false);
        if (levelSong == 1) {
            try {
                gameMusic1 = new SoundClip("data/lev1bg.wav");   // Open an audio input stream
                gameMusic1.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }

      /*  }
        else if (levelSong == 2){
            gameMusic1.stop();
            try {
                gameMusic2 = new SoundClip("data/lev2bgm.wav");   // Open an audio input stream
                gameMusic2.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }
        else if(levelSong == 3){
            gameMusic2.stop();
            try {
                gameMusic3 = new SoundClip("data/lev3bgm.wav");   // Open an audio input stream
                gameMusic3.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }*/


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
        //frame.add(controlPanel.getMainPanel(), BorderLayout.WEST);

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

        //optional: uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 900, 500);

        // start our game world simulation!





        if (PlayerController.isPause() == 1){
            System.out.println("jeff");
        }


        controller = new PlayerController(level.getPlayer(), this);
        view.addKeyListener(controller);

        mouseController = new DirectionalShooting(level.getPlayer(), view);
        view.addMouseListener(mouseController);

        StepListener tracker = new Tracker(view, level.getPlayer());
        level.addStepListener(tracker);



        //StepListener enemytracker = new EnemyTracker(view, level.getWalker());
        //level.addStepListener(enemytracker);

        System.out.println(PlayerController.isPause());

        level.start();



    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
        //System.out.println(startTime);


    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean over){
        gameOver = over;
        level.stop();
       /* gameMusic1.stop();
        gameMusic2.stop();
        gameMusic3.stop();*/
        }

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

    public void goToNextLevel(){
        if (level instanceof Level1){
            level.stop();
            level = new Level2(this);
            view.setWorld(level);

            controller.updatePlayer(level.getPlayer());
            mouseController.updatePlayer(level.getPlayer());

            level.start();
            setLevelBackground(2);
            levelSong = 2;
            gameMusic1.stop();
            try {
                gameMusic2 = new SoundClip("data/lev2bgm.wav");   // Open an audio input stream
                gameMusic2.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }
        else if (level instanceof Level2){
            level.stop();
            level = new Level3(this);
            view.setWorld(level);

            controller.updatePlayer(level.getPlayer());
            mouseController.updatePlayer(level.getPlayer());

            level.start();
            setLevelBackground(3);
            levelSong = 3;
            gameMusic2.stop();
            try {
                gameMusic3 = new SoundClip("data/lev3bgm.wav");   // Open an audio input stream
                gameMusic3.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }

        }
        else if (level instanceof Level3){
            level.stop();
            Player.setShip(true);
            level = new Level4(this);
            view.setWorld(level);

            controller.updatePlayer(level.getPlayer());
            mouseController.updatePlayer(level.getPlayer());

            level.start();
            setLevelBackground(4);
            levelSong = 4;
            gameMusic3.stop();
            try {
                gameMusic4 = new SoundClip("data/lev4bgm.wav");   // Open an audio input stream
                gameMusic4.loop();                              // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }
        else if(level instanceof Level4){
            System.out.println("Well done! Game complete.");
            System.exit(0);
        }
        if (gameOver){
            level.stop();
        }
    }

}


