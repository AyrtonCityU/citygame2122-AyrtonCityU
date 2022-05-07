package listeners.collisions;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
import dynamicBody.BossProjectile;
import dynamicBody.Player;
import dynamicBody.enemies.*;
import org.jbox2d.common.Vec2;

import static game.Level4.boss;

public class EnemyEncounter implements CollisionListener {
    private final Player player;
    private static final BodyImage hit =
            new BodyImage("data/pHit.gif", 5.5f);

    public EnemyEncounter(Player s) {
        this.player = s;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof WalkEnemy) {
            if(!player.isInvincible()) {
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                player.setLinearVelocity(new Vec2(40, 10));
                player.removeAllImages();
                player.addImage(hit);
                if (player.getPlayerHealth() == 0) {
                    e.getReportingBody().destroy();
                }
                player.setInvincible(true);
            }
        }
        if (e.getOtherBody() instanceof Flyer) {
            if(!player.isInvincible()) {
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                player.setLinearVelocity(new Vec2(20, 10));
                player.removeAllImages();
                player.addImage(hit);
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
        if (e.getOtherBody() instanceof LavaFlyer) {
            if(!player.isInvincible()) {
                player.removeAllImages();
                player.addImage(hit);
                e.getOtherBody().destroy();
                player.setInvincible(true);
            }
        }

        if(e.getOtherBody() instanceof FinalBoss){
            boss.setHurt(true);
        }
        if (Player.isShip()) {
            if (e.getOtherBody() instanceof DynamicBody) {
                if ((e.getOtherBody() instanceof FinalBoss) || (e.getOtherBody() instanceof Flyer)){
                    player.setPlayerHealth(player.getPlayerHealth() - 1);
                }
                else{
                    e.getOtherBody().destroy();
                    player.setPlayerHealth(player.getPlayerHealth() - 1);


                }
            }


        }

    }
}
