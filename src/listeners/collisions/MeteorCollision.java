package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.Projectile;
import dynamicBody.enemies.Branch;
import dynamicBody.enemies.Meteor;
import staticBody.Coins;
import staticBody.Truck;

//Meteors from level 3
public class MeteorCollision implements CollisionListener {
    private final Meteor meteor;
    private final Player player;
    public MeteorCollision (Meteor m, Player p){
        this.meteor = m;
        this.player = p;
    }

    @Override
    public void collide(CollisionEvent e) {
        if ((e.getOtherBody() instanceof Player)){
            player.setPlayerHealth(player.getPlayerHealth()-3); //Reduce the players health by 3
            e.getOtherBody().destroy();
        }

        if ((e.getOtherBody() instanceof Truck)){
            e.getReportingBody().destroy(); //Destroy the trucks on impact
            e.getOtherBody().destroy();
        }

    }
}