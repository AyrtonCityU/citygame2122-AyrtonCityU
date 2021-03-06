package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.Projectile;
import dynamicBody.enemies.Branch;
import staticBody.Coins;
import staticBody.Truck;

//Branch collision from level 1
public class BranchCollision implements CollisionListener {
    private final Player player;
    public BranchCollision (Player s){
        this.player = s;
    }

    @Override
    public void collide(CollisionEvent e) {
        if ((e.getOtherBody() instanceof Branch) && (e.getReportingBody() instanceof Player)){
            player.setPlayerHealth(player.getPlayerHealth()-1); //Reduce players health
            e.getOtherBody().destroy(); //Destroy branch
        }

        if ((e.getReportingBody() instanceof Truck)&&(e.getOtherBody() instanceof Branch)){
            e.getOtherBody().destroy();
        }

    }
}