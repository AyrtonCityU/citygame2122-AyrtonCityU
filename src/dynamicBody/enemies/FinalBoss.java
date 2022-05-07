package dynamicBody.enemies;

import city.cs.engine.*;
import dynamicBody.BossProjectile;
import game.GameLevel;
import org.jbox2d.common.Vec2;

//Final boss from level 4
public class FinalBoss extends Walker {

    private static final Shape bossShape = new BoxShape(5f,5);
    private static final BodyImage boss =
            new BodyImage("data/bossIdle.gif", 12f);

    //Checks for changing the state of the boss inside of BossEncounter
    public boolean idle = true;
    public boolean grab = false;
    public boolean punch = false;
    public boolean shoot = false;
    public boolean hurt = false;
    //Boss health
    public static int bossHp = 30;

    //Getters and setters to change and access the bosses states
    public boolean isIdle() {
        return idle;
    }
    public void setIdle(boolean idle) {
        this.idle = idle;
    }

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

    public boolean isShoot() {
        return shoot;
    }
    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isHurt() {
        return hurt;
    }
    public void setHurt(boolean hurt) {
        this.hurt = hurt;
    }

    public static int getBossHp() {
        return bossHp;
    }
    public static void setBossHp(int bossHp) {
        FinalBoss.bossHp = bossHp;
        System.out.println("Bosses health:" + bossHp);
    }


    public FinalBoss(World world){
        super(world, bossShape);
        addImage(boss);
    }

    //Method to make the boss shoot
    public void bossShoot(FinalBoss boss, GameLevel level){

        BossProjectile projectile = new BossProjectile(level, boss);
        projectile.setPosition(new Vec2(boss.getPosition().x-2, boss.getPosition().y)); //Slight off-set so the projectile fires from the boss's finger
        projectile.setGravityScale(0);
        projectile.setLinearVelocity(new Vec2(-20, 0));

    }

}
