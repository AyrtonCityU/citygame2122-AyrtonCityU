package dynamicBody.enemies;

import city.cs.engine.*;
import dynamicBody.BossProjectile;
import game.GameLevel;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

public class FinalBoss extends Walker {

    private static final Shape bossShape = new BoxShape(5f,5);

    private static final BodyImage boss =
            new BodyImage("data/bossIdle.gif", 12f);

    public boolean idle = true;

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public boolean grab = false;

    public boolean isGrab() {
        return grab;
    }

    public void setGrab(boolean grab) {
        this.grab = grab;
    }

    public boolean isPunch() {
        return punch;
    }

    public void setPunch(boolean punch) {
        this.punch = punch;
    }

    public boolean punch = false;

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean shoot = false;



    private GameLevel level;

    public FinalBoss(World world){
        super(world, bossShape);
        addImage(boss);
/*
        setPosition(new Vec2(5,-6));
*/

    }

    public void bossShoot(FinalBoss boss, GameLevel level){
        BossProjectile projectile = new BossProjectile(level, this);
        /*projectile.setPosition(new Vec2((float) (boss.getPosition().x-2), boss.getPosition().y));
        projectile.setGravityScale(0);
        projectile.setLinearVelocity(new Vec2(-20, 0));*/

    }

}
