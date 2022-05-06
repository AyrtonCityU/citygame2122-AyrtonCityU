package game;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import dynamicBody.Player;
import city.cs.engine.UserView;
import dynamicBody.enemies.FinalBoss;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private final Image background;
    private final Image background2;
    private final Image background3;
    private final Image background4;
    private final Image heart;
    private final Image gheart;
    private final Image ftree;
    private final Image berg;
    private final Image progress;
    private final Image bosshp1;
    private final Image bosshp2;
    private final Image bosshp3;
    private final Image bosshp4;
    private final Image bosshp5;
    private final Image bosshp6;
    private final Image bosshp7;
    private final Image score;


    // private final Image leaves;


    GameWorld world = new GameWorld();
    Player player = world.getPlayer();

    public GameView(GameLevel level, int width, int height) {

        super(level, width, height);
        background = new ImageIcon("data/backgroundalt.png").getImage();
        background2 = new ImageIcon("data/lev2Bg.png").getImage();
        background3 = new ImageIcon("data/lev3bg.png").getImage();
        background4 = new ImageIcon("data/lev4Bg.gif").getImage();
        heart = new ImageIcon("data/heart.png").getImage();
        gheart = new ImageIcon("data/gheart.png").getImage();
        ftree = new ImageIcon("data/ftree.png").getImage();
        berg = new ImageIcon("data/iceberg.png").getImage();
        progress = new ImageIcon("data/progress.gif").getImage();
        //leaves = new ImageIcon("data/speed.gif").getImage();
        bosshp1 = new ImageIcon("data/bosshp1.png").getImage();
        bosshp2 = new ImageIcon("data/bosshp2.png").getImage();
        bosshp3 = new ImageIcon("data/bosshp3.png").getImage();
        bosshp4 = new ImageIcon("data/bosshp4.png").getImage();
        bosshp5 = new ImageIcon("data/bosshp5.png").getImage();
        bosshp6 = new ImageIcon("data/bosshp6.png").getImage();
        bosshp7 = new ImageIcon("data/bosshp7.png").getImage();
        score = new ImageIcon("data/score.png").getImage();


    }


    @Override
    protected void paintBackground(Graphics2D g) {

        if (Game.getLevelBackground() == 1) {
            g.drawImage(background, 0, 0, this);
        } else if (Game.getLevelBackground() == 2) {
            g.drawImage(background2, 0, 0, this);
        } else if (Game.getLevelBackground() == 3) {
            g.drawImage(background3, 0, 0, this);
        } else if (Game.getLevelBackground() == 4) {
            g.drawImage(background4, 0, 0, this);
        }

    }

    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        if (Game.getLevelBackground() == 1) {
            g.drawImage(ftree, -240, -200, 500, 900, this);
            g.drawImage(ftree, 800, -200, 500, 900, this);
        } else if (Game.getLevelBackground() == 2) {
            g.drawImage(berg, -200, 300, 410, 500, this);
            g.drawImage(berg, 800, 300, 410, 500, this);

        } else if (Game.getLevelBackground() == 3) {

        }

        if (player.getPlayerHealth() == 3) {
            g.drawImage(heart, 40, 3, 40, 40, this);
            g.drawImage(heart, 80, 3, 40, 40, this);
            g.drawImage(heart, 120, 3, 40, 40, this);
        } else if (player.getPlayerHealth() == 2) {
            g.drawImage(heart, 40, 3, 40, 40, this);
            g.drawImage(heart, 80, 3, 40, 40, this);
        } else if (player.getPlayerHealth() == 1) {
            g.drawImage(heart, 40, 3, 40, 40, this);
        }

        if (player.getPlayerHealth() > 50) {
            g.drawImage(gheart, 40, 3, 100, 50, this);
        }

        g.drawImage(progress, 400, 0, 183, 58, this);
        //g.drawImage(leaves, 0, 0, 1100, 550, this);

        if (player.getPlayerHealth() == 0) {
            g.drawString("GAME OVER", this.getWidth() / 2, this.getHeight() / 2);
        }

        if (Level4.isBossSpawn()) {
            if(FinalBoss.getBossHp()<31 && FinalBoss.getBossHp()>25) {
                g.drawImage(bosshp1, 275, 420, 604, 149, this);
            }
            if(FinalBoss.getBossHp()<26 && FinalBoss.getBossHp()>20) {
                g.drawImage(bosshp2, 275, 420, 604, 149, this);
            }
            if(FinalBoss.getBossHp()<21 && FinalBoss.getBossHp()>15) {
                g.drawImage(bosshp3, 275, 420, 604, 149, this);
            }
            if(FinalBoss.getBossHp()<16 && FinalBoss.getBossHp()>10) {
                g.drawImage(bosshp4, 275, 420, 604, 149, this);
            }
            if(FinalBoss.getBossHp()<11 && FinalBoss.getBossHp()>5) {
                g.drawImage(bosshp5, 275, 420, 604, 149, this);
            }
            if(FinalBoss.getBossHp()<6 && FinalBoss.getBossHp()>0) {
                g.drawImage(bosshp6, 275, 420, 604, 149, this);
            }
            if(FinalBoss.getBossHp()==0){
                g.drawImage(bosshp7, 275, 420, 604, 149, this);
            }
        }

        g.drawImage(score, 700, 3, 60, 50, this);
        g.drawString(Integer.toString(Player.getCoinsCollected()), 790,30);

       /* if (GameLevel.getPlayer().getCoinsCollected() == 0) {
            g.drawImage(heart, 70, 0, 40, 40, this);
        } else if (GameLevel.getPlayer().getCoinsCollected() == 1) {
            repaint();
            g.drawImage(heart, 70, 0, 40, 40, this);
            g.drawImage(heart, 90, 0, 40, 40, this);
        } else if (GameLevel.getPlayer().getCoinsCollected() == 2) {
            repaint();
            g.drawImage(ftree, 50, 3, 40, 40, this);
        }*/

    }
}







