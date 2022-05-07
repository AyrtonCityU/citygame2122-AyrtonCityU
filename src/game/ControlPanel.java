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
    private JButton STORYButton;
    private JButton CONTROLSButton;
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
        STORYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!game.isPlot()){
                    game.setPlot(true);
                }
                else if(game.isPlot()){
                    game.setPlot(false);
                }

            }
        });
        CONTROLSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!game.isControls()){
                    game.setControls(true);
                }
                else if(game.isControls()){
                    game.setControls(false);
                }
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
