package listeners.mkb;
import city.cs.engine.BodyImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;

import dynamicBody.Player;
import game.Game;
import game.HighScoreReader;
import game.HighScoreWriter;
import game.NameInput;
import items.JumpBoots;
import org.jbox2d.common.Vec2;

public class PlayerController implements KeyListener {

    private static int pause = 0;
    private Player player;
    private final Game game;
    private static  float WALKING_SPEED = 20;
    private static final float RUNNING_SPEED = 40;
    private static final float JUMP_SPEED = 40;
    private static final Vec2 DOWN = new Vec2(0,-5);

    public static int isPause() {
        return pause;
    }

    public void setPause(int pause) {
        PlayerController.pause = pause;
    }

    BodyImage jump = new BodyImage("data/pJump.gif", 5.5f);
    BodyImage still = new BodyImage("data/pIdle.gif", 5.5f);

   BodyImage shipstill = new BodyImage("data/shipRight.png", 4.5f);
   BodyImage shipUp = new BodyImage("data/shipUp.gif", 4.5f);
    BodyImage shipDown = new BodyImage("data/shipDown.gif", 4.5f);





    // public static void main(String[] args) {
        //BodyImage[] rights = new BodyImage[10];
       // for (int i = 30; i<40; i++)
        //{
           // rights[i] =  new BodyImage("data/tile0" + i +".png", 4.5f);
        //}
        //System.out.println(rights);
   // }

    public PlayerController(Player player, Game game) {
        this.player = player;
        this.game = game;

        if (player.getPlayerHealth()==0){
            game.setGameOver(true);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted

        if(!player.isShip()) {
            if (code == KeyEvent.VK_A) {
                player.startWalking(-WALKING_SPEED);

            } else if (code == KeyEvent.VK_D) {

                player.startWalking(WALKING_SPEED);
            } else if (code == KeyEvent.VK_W) {
                if (player.isShip()) {
                    player.setLinearVelocity(new Vec2(0, 5));
                }
            } else if (code == KeyEvent.VK_SPACE) {
                    player.jump(JumpBoots.getJ());
                    if (player.getPosition().y > 5) {
                        player.setLinearVelocity(new Vec2(0, 20));
                    }
                    player.removeAllImages();
                    player.addImage(jump);

            } else if (code == KeyEvent.VK_J) {
                //spawn projectile
                player.removeAllImages();
                if (player.direction.equals("left")) {
                    player.addImage(new BodyImage("data/shootleft.png", 4.5f));
                } else {
                    player.addImage(new BodyImage("data/shootright.png", 4.5f));
                }
                //player.shoot();
            } else if (code == KeyEvent.VK_Q) {
                player.getBackpack().toggle();
            }
        /*else if (code == KeyEvent.VK_F){
            player.getBackpack().getCurrentItem().operate();
        }*/
            else if (code == KeyEvent.VK_ESCAPE) {
                game.toggleMenu();
            }

        }

        if (player.isShip()){
            if (code == KeyEvent.VK_A) {
                player.setLinearVelocity(new Vec2(-10,0));

            }
            else if (code == KeyEvent.VK_D) {
                player.setLinearVelocity(new Vec2(10,0));
            }
            else if (code == KeyEvent.VK_W) {
                player.setLinearVelocity(new Vec2(0, 10));
                player.removeAllImages();
                player.addImage(shipUp);
                }
            else if (code == KeyEvent.VK_S) {
                player.setLinearVelocity(new Vec2(0, -10));
                player.removeAllImages();
                player.addImage(shipDown);
            }
            else if (code == KeyEvent.VK_E) {
                player.setLinearVelocity(new Vec2(3, 3));
            }
            else if (code == KeyEvent.VK_C) {
                player.setLinearVelocity(new Vec2(3, -3));
            }
            else if (code == KeyEvent.VK_SPACE) {
               player.shipShoot(new Vec2(10,5));

            }
             else if (code == KeyEvent.VK_Q) {
                player.getBackpack().toggle();
            }
            else if (code == KeyEvent.VK_F){
            player.getBackpack().getCurrentItem().operate();
            }
            else if (code == KeyEvent.VK_ESCAPE) {
                game.toggleMenu();
            }

        }

        if (code == KeyEvent.VK_H) {
            //SHOW HI SCORES
            HighScoreReader reader = new HighScoreReader("data/HiScores.txt");
            try {
                reader.readScores();
                reader.sortByScore();
                reader.displayScores(10);
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        } else if (code == KeyEvent.VK_N) {
            try {
                HighScoreWriter fw = new HighScoreWriter("data/HiScores.txt");
                if (player.isHasName()) {
                    fw.writeHighScore(player.getName(), player.getCoinsCollected());
                } else {
                    System.out.println("You have to complete a run before submitting a score!");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (!player.isShip()) {
            if (code == KeyEvent.VK_A) {
                player.stopWalking();
                WALKING_SPEED = 10;
                player.removeAllImages();
                player.addImage(still);
                player.setLinearVelocity(new Vec2(0, 0));
            } else if (code == KeyEvent.VK_D) {
                player.stopWalking();
                WALKING_SPEED = 10;
                player.removeAllImages();
                player.addImage(still);
                player.setLinearVelocity(new Vec2(0, 0));
            } else if (code == KeyEvent.VK_SPACE) {
                player.setLinearVelocity(DOWN);
                player.removeAllImages();
                player.addImage(still);
            }
        }

        if (player.isShip()) {
            if (code == KeyEvent.VK_W) {
                player.setLinearVelocity(new Vec2(0, 0));
                player.removeAllImages();
                player.addImage(shipstill);
            }
            else if (code == KeyEvent.VK_A) {
                player.setLinearVelocity(new Vec2(0, 0));

            }
            else if (code == KeyEvent.VK_S) {
                player.setLinearVelocity(new Vec2(0, 0));
                player.removeAllImages();
                player.addImage(shipstill);
            }
            else if (code == KeyEvent.VK_D) {
                player.setLinearVelocity(new Vec2(0, 0));
            }

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("Jeff");
    }

    public void updatePlayer(Player newPlayer){
        player = newPlayer;
    }
}
