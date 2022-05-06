package listeners.mkb;
import city.cs.engine.BodyImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;

import dynamicBody.Player;
import dynamicBody.Projectile;
import game.*;
import items.JumpBoots;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

public class PlayerController implements KeyListener {

    private static int pause = 0;
    private Player player;
    private final Game game;
    private static  float WALKING_SPEED = 20;
    private static final float RUNNING_SPEED = 40;
    private static final float JUMP_SPEED = 40;
    private static final Vec2 DOWN = new Vec2(0,-5);
    private boolean left = false;
    private boolean right = true;

    public static int isPause() {
        return pause;
    }

    public void setPause(int pause) {
        PlayerController.pause = pause;
    }

    BodyImage jump = new BodyImage("data/pJump.gif", 5.5f);
    BodyImage jumpL = new BodyImage("data/pJumpL.gif", 5.5f);
    BodyImage still = new BodyImage("data/pIdle.gif", 5.5f);
    BodyImage stillL = new BodyImage("data/pIdleL.gif", 5.5f);


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
            }
             else if (code == KeyEvent.VK_SPACE) {
                    player.jump(45);
                    player.removeAllImages();
                    if (right) {

                        player.addImage(jump);
                        if (player.getPosition().y > -4 && !player.isJumped() && player.getBackpack().getCurrentItem().getType() == "JumpBoots") {
                            player.setLinearVelocity(new Vec2(0, 40));
                            player.removeAllImages();
                            player.addImage(jump);
                            player.setJumped(true);
                        }
                    }
                    else if (left){
                        player.addImage(jumpL);
                        if (player.getPosition().y > -4 && !player.isJumped() && player.getBackpack().getCurrentItem().getType() == "JumpBoots") {
                            player.setLinearVelocity(new Vec2(0, 40));
                            player.removeAllImages();
                            player.addImage(jumpL);
                            player.setJumped(true);
                        }
                    }


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
                player.setLinearVelocity(new Vec2(-15,0));

            }
            else if (code == KeyEvent.VK_D) {
                player.setLinearVelocity(new Vec2(15,0));
            }
            else if (code == KeyEvent.VK_W) {
                player.setLinearVelocity(new Vec2(0, 15));
                player.removeAllImages();
                player.addImage(shipUp);
                }
            else if (code == KeyEvent.VK_S) {
                player.setLinearVelocity(new Vec2(0, -15));
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
               player.shipShoot(player, Game.getLevel());

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
                /*WALKING_SPEED = 10;*/
                player.removeAllImages();
                player.addImage(stillL);
                player.setLinearVelocity(new Vec2(0, 0));
                left = true;
                right = false;
            } else if (code == KeyEvent.VK_D) {
                player.stopWalking();
/*
                WALKING_SPEED = 10;
*/
                player.removeAllImages();
                player.addImage(still);
                player.setLinearVelocity(new Vec2(0, 0));
                left = false;
                right = true;
            } else if (code == KeyEvent.VK_SPACE) {
                player.setLinearVelocity(DOWN);
                player.removeAllImages();
                if (right){
                    player.addImage(still);
                }
                else{
                    player.addImage(stillL);
                }
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
            else if (code == KeyEvent.VK_SPACE) {

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
