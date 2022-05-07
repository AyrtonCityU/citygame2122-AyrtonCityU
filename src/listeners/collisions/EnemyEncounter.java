package listeners.collisions;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.BossProjectile;
import dynamicBody.Player;
import dynamicBody.enemies.*;
import org.jbox2d.common.Vec2;


public class EnemyEncounter implements CollisionListener {
    private final Player player;

    //This is the hit animation for the boss on level 4
    private static final BodyImage hit =
            new BodyImage("data/pHit.gif", 5.5f);

    public EnemyEncounter(Player s) {
        this.player = s;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof WalkEnemy) {
                if (!player.isInvincible()) { //If the player isn't invincible
                    player.setPlayerHealth(player.getPlayerHealth() - 1); //They will take damage
                    player.setLinearVelocity(new Vec2(40, 10)); //And be pushed away
                    if (!Player.isShip()) { //If the player isn't a ship
                        player.removeAllImages();
                        player.addImage(hit); //Player the player hurt animation
                    }
                    if (player.getPlayerHealth() == 0) {
                        e.getReportingBody().destroy(); //Destroy the player if 0 health
                    }
                    player.setInvincible(true); //Set invincible, this lasts a few frames
                }
            }

        if (e.getOtherBody() instanceof Flyer) {

            if(!player.isInvincible()) {
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                player.setLinearVelocity(new Vec2(20, 10));
                if (!Player.isShip()) {
                    player.removeAllImages();
                    player.addImage(hit);
                }
                if (player.getPlayerHealth() == 0) {
                    e.getReportingBody().destroy();
                }
                player.setInvincible(true);
            }
        }
        if (e.getOtherBody() instanceof IceBoss) {
            e.getOtherBody().destroy();
            ((IceBoss) e.getOtherBody()).setAlive(false);
        }
        if (e.getOtherBody() instanceof LavaFlyer) { //Didn't add in ship here as no LavaFlyer on ship level
            if(!player.isInvincible()) {
                player.removeAllImages();
                player.addImage(hit);
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                e.getOtherBody().destroy();
                player.setInvincible(true);
            }
        }

        if(e.getOtherBody() instanceof FinalBoss){
            if(!player.isInvincible()) {
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                player.setLinearVelocity(new Vec2(20, 10));
                if (player.getPlayerHealth() == 0) {
                    e.getReportingBody().destroy();
                }
                player.setInvincible(true);
            }
        }


        if (e.getOtherBody() instanceof BossProjectile) {
            e.getOtherBody().destroy();
            if(!player.isInvincible()) {
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                if (player.getPlayerHealth() == 0) {
                    e.getReportingBody().destroy();
                }
                player.setInvincible(true);
            }
        }

    }
}
