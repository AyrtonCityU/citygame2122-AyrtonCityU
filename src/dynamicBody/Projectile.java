package dynamicBody;

import city.cs.engine.*;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

public class Projectile extends DynamicBody {

    public String direction;
    private Player player;


    public Projectile(World w, Player player) {
        super(w);
        DynamicBody projectile = new DynamicBody(this.getWorld(), new BoxShape(2, 1));
        ProjectileCollision projectileCollision = new ProjectileCollision(player);
        projectile.addCollisionListener(projectileCollision);
        projectile.addImage(new BodyImage("data/shipshot.gif", 3f));
        projectile.setGravityScale(0);
        projectile.setAlwaysOutline(false);
        projectile.setPosition(new Vec2((float) (player.getPosition().x + 5), player.getPosition().y));
        projectile.setLinearVelocity(new Vec2(30, 0));
    }


}
