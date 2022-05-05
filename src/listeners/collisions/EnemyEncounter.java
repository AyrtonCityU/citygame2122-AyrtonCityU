package listeners.collisions;

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

    public EnemyEncounter(Player s) {
        this.player = s;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof WalkEnemy) {
            player.setPlayerHealth(player.getPlayerHealth() - 1);
            player.setLinearVelocity(new Vec2(20, 10));
            if (player.getPlayerHealth() == 0) {
                e.getReportingBody().destroy();
            }
        }
        if (e.getOtherBody() instanceof Flyer) {
            player.setPlayerHealth(player.getPlayerHealth() - 1);
            player.setLinearVelocity(new Vec2(20, 10));
            if (player.getPlayerHealth() == 0) {
                e.getReportingBody().destroy();
            }
        }
        if (e.getOtherBody() instanceof IceBoss) {
            e.getOtherBody().destroy();
            ((IceBoss) e.getOtherBody()).setAlive(false);
        }
        if (e.getOtherBody() instanceof LavaFlyer) {
            e.getOtherBody().destroy();
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
