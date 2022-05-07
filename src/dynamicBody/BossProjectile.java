package dynamicBody;

import city.cs.engine.*;
import dynamicBody.enemies.FinalBoss;
import listeners.collisions.BossProjectileCollision;
import listeners.collisions.EnemyEncounter;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

public class BossProjectile extends DynamicBody {

    public String direction;
    private Player player;



    public BossProjectile(World w, FinalBoss finalBoss) {
        super(w);
        DynamicBody projectile = new DynamicBody(this.getWorld(), new BoxShape(3,2));
        projectile.addImage(new BodyImage("data/bossProjectile.gif", 4f));
        projectile.setAlwaysOutline(false);
        BossProjectileCollision projectileCollision = new BossProjectileCollision(finalBoss);
        projectile.addCollisionListener(projectileCollision);
        projectile.setPosition(new Vec2((float) (finalBoss.getPosition().x-2), finalBoss.getPosition().y));
        projectile.setGravityScale(0);
        projectile.setLinearVelocity(new Vec2(-20, 0));
    }


}
