package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import dynamicBody.Player;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.LavaFlyer;
import dynamicBody.enemies.WalkEnemy;
import game.GameLevel;
import org.jbox2d.common.Vec2;

//Tracker for flyer in 3rd level

public class LavaFlyerTracker implements StepListener {
    private final GameLevel level;
    private final LavaFlyer flyer;

    private final Player player;



    public LavaFlyerTracker(GameLevel level, LavaFlyer flyer, Player player) {
        this.level = level;
        this.flyer = flyer;
        this.player = player;
    }

    public void preStep(StepEvent e){
    }

    public void postStep(StepEvent e){

   if (flyer.getPosition().x < player.getPosition().x ){ //If the flyer is directly above the player
                flyer.setLinearVelocity(new Vec2(0,-10)); //Instantly move down towards the player
                flyer.removeAllImages();
                flyer.addImage(new BodyImage("data/lavarock.png", 3.5f));//Add lavarock image
                flyer.stopWalking();
                flyer.setGravityScale(10);
            }
   if (flyer.getPosition().y < -7){
       flyer.destroy();
   }
        }

 }

