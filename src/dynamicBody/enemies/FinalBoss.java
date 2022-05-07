package dynamicBody.enemies;

import city.cs.engine.*;
import dynamicBody.BossProjectile;
import game.GameLevel;
import org.jbox2d.common.Vec2;

/**Class for the final boss from level 4*/
public class FinalBoss extends Walker {

    private static final Shape bossShape = new BoxShape(5f,5);
    private static final BodyImage boss =
            new BodyImage("data/bossIdle.gif", 12f);

    //Checks for changing the state of the boss inside of BossEncounter
    /**Boss starts in idle state*/
    public boolean idle = true;
    /**Boss starts in false grab state*/
    public boolean grab = false;
    /**Boss starts in false punch state*/
    public boolean punch = false;
    /**Boss starts in false shoot state*/
    public boolean shoot = false;
    /**Boss starts in false hurt state*/
    public boolean hurt = false;
    /**Boss Health*/
    public static int bossHp = 30;

    //Getters and setters to change and access the bosses states
    /**Returns the bosses idle state*/
    public boolean isIdle() {
        return idle;
    }
    /**Sets the bosses idle state*/
    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    /**Returns the bosses grab state*/
    public boolean isGrab() {
        return grab;
    }
    /**Sets the bosses grab state*/
    public void setGrab(boolean grab) {
        this.grab = grab;
    }

    /**Returns the bosses punch state*/
    public boolean isPunch() {
        return punch;
    }
    /**Sets the bosses punch state*/
    public void setPunch(boolean punch) {
        this.punch = punch;
    }

    /**Returns the bosses shoot state*/
    public boolean isShoot() {
        return shoot;
    }
    /**Sets the bosses punch state*/
    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    /**Returns the bosses hurt state*/
    public boolean isHurt() {
        return hurt;
    }
    /**Sets the bosses hurt state*/
    public void setHurt(boolean hurt) {
        this.hurt = hurt;
    }

    /**Gets the bosses current health*/
    public static int getBossHp() {
        return bossHp;
    }
    /**Sets the bosses current health*/
    public static void setBossHp(int bossHp) {
        FinalBoss.bossHp = bossHp;
        System.out.println("Bosses health:" + bossHp);
    }

    /**Creates the final boss*/
    public FinalBoss(World world){
        super(world, bossShape);
        addImage(boss);
    }

    /**Method to make the boss shoot*/
    public void bossShoot(FinalBoss boss, GameLevel level){

        BossProjectile projectile = new BossProjectile(level, boss);
        projectile.setPosition(new Vec2(boss.getPosition().x-2, boss.getPosition().y)); //Slight off-set so the projectile fires from the boss's finger
        projectile.setGravityScale(0);
        projectile.setLinearVelocity(new Vec2(-20, 0));

    }

}
