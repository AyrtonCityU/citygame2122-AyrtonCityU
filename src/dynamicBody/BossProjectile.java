package dynamicBody;

import city.cs.engine.*;
import dynamicBody.enemies.FinalBoss;
import listeners.collisions.BossProjectileCollision;
import listeners.collisions.EnemyEncounter;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

//Projectiles fired from the boss in the final level
public class BossProjectile extends DynamicBody {


    public BossProjectile(World w, FinalBoss finalBoss) {
        super(w);
        DynamicBody projectile = new DynamicBody(this.getWorld(), new BoxShape(3,2));
        projectile.addImage(new BodyImage("data/bossProjectile.gif", 4f));
        BossProjectileCollision projectileCollision = new BossProjectileCollision(finalBoss); //adding the projectile listeners
        projectile.addCollisionListener(projectileCollision);
        projectile.setPosition(new Vec2(finalBoss.getPosition().x-2, finalBoss.getPosition().y)); //fires from his fingertips
        projectile.setGravityScale(0); //No gravity, final level is in space so wouldn't make sense!
        projectile.setLinearVelocity(new Vec2(-20, 0));
    }


}
