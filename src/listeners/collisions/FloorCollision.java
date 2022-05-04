package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.WalkEnemy;
import org.jbox2d.common.Vec2;
import staticBody.Ground;

public class FloorCollision implements CollisionListener{
    private final Player player;
    public FloorCollision(Player s){ this.player = s;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ground) {
            player.setPlayerHealth(0);
                e.getReportingBody().destroy();

            }
        }



    }


