package game;

import dynamicBody.Player;
import listeners.mkb.PlayerController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {
    public JPanel mainPanel;
    private JButton pauseResumeButton;
    private JButton muteMusicButton;
    private JButton easyModeButton;
    private JButton quitButton;
    private PlayerController controller;

    private final Game game;
    private GameView view;
    private static boolean musicPaused = false;

    public ControlPanel(Game game, GameView view){
        this.game = game;
        muteMusicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!musicPaused) {
                    game.gameMusic.pause();
                    musicPaused = true;
                }
                else if (musicPaused) {
                    game.gameMusic.loop();
                    musicPaused = false;
                }
            }
        });
        pauseResumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.goToNextLevel();

            }
        });
        easyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Player.playerHealth = 1000;
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
