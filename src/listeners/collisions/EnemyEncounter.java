package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.IceBoss;
import dynamicBody.enemies.LavaFlyer;
import dynamicBody.enemies.WalkEnemy;
import org.jbox2d.common.Vec2;

public class EnemyEncounter implements CollisionListener {
    private final Player player;
    public EnemyEncounter (Player s){
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
            player.setPlayerHealth(player.getPlayerHealth()-1);
            player.setLinearVelocity(new Vec2(20,10));
            if (player.getPlayerHealth() == 0) {
                e.getReportingBody().destroy();
            }
        }
        if(e.getOtherBody() instanceof IceBoss){
            e.getOtherBody().destroy();
            ((IceBoss) e.getOtherBody()).setAlive(false);
        }
        if (e.getOtherBody() instanceof LavaFlyer) {
            e.getOtherBody().destroy();
        }



        }
}
