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

//Controls the player with the keyboard
public class PlayerController implements KeyListener {

    private static int pause = 0;
    private Player player;
    private final Game game;
    private static final float WALKING_SPEED = 20;
    private static final Vec2 DOWN = new Vec2(0,-5);
    private boolean left = false;
    private boolean right = true;

    public static int isPause() {
        return pause;
    }
    public void setPause(int pause) {
        PlayerController.pause = pause;
    }

    //Jumping images and also idle images for both left and right
    BodyImage jump = new BodyImage("data/pJump.gif", 5.5f);
    BodyImage jumpL = new BodyImage("data/pJumpL.gif", 5.5f);
    BodyImage still = new BodyImage("data/pIdle.gif", 5.5f);
    BodyImage stillL = new BodyImage("data/pIdleL.gif", 5.5f);

    //Images for final level player. Idle, moving up and moving down
    BodyImage shipstill = new BodyImage("data/shipRight.png", 4.5f);
    BodyImage shipUp = new BodyImage("data/shipUp.gif", 4.5f);
    BodyImage shipDown = new BodyImage("data/shipDown.gif", 4.5f);




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

        //These only apply if the player is NOT the ship
        if(!Player.isShip()) {
            if (code == KeyEvent.VK_A) {
                player.startWalking(-WALKING_SPEED); //Minus walking speed if holding A (move left)

            } else if (code == KeyEvent.VK_D) {

                player.startWalking(WALKING_SPEED);//Move right
            }
             else if (code == KeyEvent.VK_SPACE) {
                    player.jump(45); //Jump
                    player.removeAllImages();
                    if (right) {
                        player.addImage(jump);
                        //Code for double jump
                        if (player.getPosition().y > -4 && !player.isJumped() && Player.getBackpack().getCurrentItem().getType() == "JumpBoots") {
                            player.setLinearVelocity(new Vec2(0, 40));
                            player.removeAllImages();
                            player.addImage(jump);
                            player.setJumped(true); //This will only become false again when the player touches the ground
                        }
                    }
                    else if (left){
                        player.addImage(jumpL);
                        //Code for double jump
                        if (player.getPosition().y > -4 && !player.isJumped() && Player.getBackpack().getCurrentItem().getType() == "JumpBoots") {
                            player.setLinearVelocity(new Vec2(0, 40));
                            player.removeAllImages();
                            player.addImage(jumpL);
                            player.setJumped(true); //This will only become false again when the player touches the ground
                        }
                    }
               }
             else if (code == KeyEvent.VK_Q) {
                Player.getBackpack().toggle(); //Toggles backpack item
            }
            else if (code == KeyEvent.VK_ESCAPE) {
                game.toggleMenu();
                game.setPlot(false);
                game.setControls(false); //Open or close the menu and ensure controls and plot are closed
            }

        }

        //These only apply if the player is the ship
        if (Player.isShip()){
            if (code == KeyEvent.VK_A) {
                player.setLinearVelocity(new Vec2(-15,0));//Move left
            }
            else if (code == KeyEvent.VK_D) {
                player.setLinearVelocity(new Vec2(15,0));//Move right
            }
            else if (code == KeyEvent.VK_W) {
                player.setLinearVelocity(new Vec2(0, 15));//Move up
                player.removeAllImages();
                player.addImage(shipUp);
                }
            else if (code == KeyEvent.VK_S) {
                player.setLinearVelocity(new Vec2(0, -15));//Move down
                player.removeAllImages();
                player.addImage(shipDown);
            }
            else if (code == KeyEvent.VK_SPACE) {
               player.shipShoot(player, Game.getLevel());//Shoot

            }
            else if (code == KeyEvent.VK_ESCAPE) {
                game.toggleMenu();//Toggle the menu
            }

        }

        //These work regardless of player or ship
        if (code == KeyEvent.VK_H) {
            //SHOW HI SCORES
            HighScoreReader reader = new HighScoreReader("data/HiScores.txt");
            try {
                reader.readScores();//Read scores
                reader.sortByScore();//Sort the scores
                reader.displayScores(5);//Display top 5 scores
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        } else if (code == KeyEvent.VK_N) {
            try {
                HighScoreWriter fw = new HighScoreWriter("data/HiScores.txt");//Write to the HiScores.txt
                if (player.isHasName()) {
                    fw.writeHighScore(player.getName(), Player.getCoinsCollected());
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

        //If the player is not the ship
        if (!Player.isShip()) {
            if (code == KeyEvent.VK_A) {
                player.stopWalking();
                player.removeAllImages();
                player.addImage(stillL);//Add standing still image
                player.setLinearVelocity(new Vec2(0, 0)); //Stop movement completely
                left = true; //Left is true as last button pressed was A
                right = false;
            } else if (code == KeyEvent.VK_D) {
                player.stopWalking();
                player.removeAllImages();
                player.addImage(still);
                player.setLinearVelocity(new Vec2(0, 0));
                left = false;
                right = true; //Right is true as last button pressed was D
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

        //These all apply the shipstill image after a movement unless moved left or right
        if (Player.isShip()) {
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
    }

    public void updatePlayer(Player newPlayer){
        player = newPlayer;
    }
}
