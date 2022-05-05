package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.BossProjectile;
import dynamicBody.Player;
import dynamicBody.Projectile;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.Snowball;
import dynamicBody.enemies.WalkEnemy;
import org.jbox2d.common.Vec2;
import staticBody.Truck;

public class ProjectileCollision implements CollisionListener{
    public Projectile projectile;
    public WalkEnemy enemy;
    public WalkEnemy penguin;
    private Flyer flyer;
    public Player player;
    public ProjectileCollision(Player s){
        this.player = s;
    }





    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof WalkEnemy) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
            player.setCoins(player.getCoinsCollected()+5);
        }
        if (collisionEvent.getOtherBody() instanceof Flyer) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
        }
        if (collisionEvent.getOtherBody() instanceof Truck) {
            collisionEvent.getReportingBody().destroy();
        }
        if (collisionEvent.getOtherBody() instanceof Snowball) {
            collisionEvent.getOtherBody().destroy();
        }
    }
}
