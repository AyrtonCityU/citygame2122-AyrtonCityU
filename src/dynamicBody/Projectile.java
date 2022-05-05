package dynamicBody;

import city.cs.engine.*;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

public class Projectile extends DynamicBody {

    public String direction;
    private Player player;


    public Projectile(World w, Player player) {
        super(w);
        DynamicBody projectile = new DynamicBody(this.getWorld(), new BoxShape(2,1));
        projectile.addImage(new BodyImage("data/bossProjectile.gif", 1f));
        ProjectileCollision projectileCollision2 = new ProjectileCollision(player);
        addCollisionListener(projectileCollision2);
        projectile.setAlwaysOutline(false);
        projectile.setGravityScale(0);
        projectile.setPosition(new Vec2((float) (player.getPosition().x+5), player.getPosition().y));
        projectile.setLinearVelocity(new Vec2(30, 0));
    }


}
