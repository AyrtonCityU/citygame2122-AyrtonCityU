package game;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import dynamicBody.Player;
import city.cs.engine.UserView;

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




    }


    @Override
    protected void paintBackground(Graphics2D g) {

        if(Game.getLevelBackground() ==1){
            g.drawImage(background, 0, 0, this);
        }
        else if(Game.getLevelBackground() ==2){
            g.drawImage(background2, 0, 0, this);
        }
        else if(Game.getLevelBackground() ==3){
            g.drawImage(background3, 0, 0, this);
        }
        else if(Game.getLevelBackground() ==4){
            g.drawImage(background4, 0, 0, this);
        }

    }

    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        if(Game.getLevelBackground() ==1){
            g.drawImage(ftree, -240, -200, 500, 900, this);
            g.drawImage(ftree, 800, -200, 500, 900, this);
        }
        else if (Game.getLevelBackground() ==2) {
            g.drawImage(berg, -200, 300, 410, 500, this);
            g.drawImage(berg, 800, 300, 410, 500, this);

        }
        else if (Game.getLevelBackground() ==3) {

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

        if(player.getPlayerHealth()>50){
            g.drawImage(gheart, 40, 3, 100, 50, this);
            }

        g.drawImage(progress, 400, 0, 183, 58, this);
        //g.drawImage(leaves, 0, 0, 1100, 550, this);

        if (player.getPlayerHealth() == 0){
            g.drawString("GAME OVER", this.getWidth()/2, this.getHeight()/2);
        }

       /* if (world.getPlayer().getCoinsCollected() == 0) {
            g.drawImage(heart, 70, 0, 40, 40, this);
        } else if (world.getPlayer().getCoinsCollected() == 1) {
            repaint();
            g.drawImage(heart, 70, 0, 40, 40, this);
            g.drawImage(heart, 90, 0, 40, 40, this);
        } else if (world.getPlayer().getCoinsCollected() == 2) {
            repaint();
            g.drawImage(ftree, 50, 3, 40, 40, this);
        }
*/
    }


}




