package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.Projectile;
import dynamicBody.enemies.FinalBoss;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.Snowball;
import dynamicBody.enemies.WalkEnemy;
import staticBody.Coins;
import staticBody.Hearts;
import staticBody.Truck;

import static game.levels.Level4.boss;

public class ProjectileCollision implements CollisionListener{
    public Projectile projectile;
    public Player player;
    public ProjectileCollision(Player s){
        this.player = s;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof WalkEnemy) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
            Player.setCoins(Player.getCoinsCollected()+5); //Add 5 score if projectile hits a walk enemy
        }
        if (collisionEvent.getOtherBody() instanceof Flyer) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
            if (!Player.isShip()) {
                collisionEvent.getReportingBody().destroy();
                Player.setCoins(Player.getCoinsCollected() + 5);
            }
            Player.setCoins(Player.getCoinsCollected() + 5); //Add 5 score if projectile hits a flyer



        }
        if (collisionEvent.getOtherBody() instanceof Truck) {
            collisionEvent.getReportingBody().destroy();//Destroys bullets if they touch trucks
        }
        if (collisionEvent.getOtherBody() instanceof Snowball) {
            collisionEvent.getOtherBody().destroy(); //Destroys snowballs if bullets touch them
        }

        if(collisionEvent.getOtherBody() instanceof FinalBoss){
            boss.setHurt(true); //Sets the final boss to hurt (plays the hurt animation)
            collisionEvent.getReportingBody().destroy();
            FinalBoss.setBossHp(FinalBoss.getBossHp()-1); //Reduces health by 1
            if (FinalBoss.getBossHp()==0){
                collisionEvent.getOtherBody().destroy(); //Kills final boss
                Player.setCoins(Player.getCoinsCollected()+100);//Adds 100 to score
            }
        }
        if (collisionEvent.getOtherBody() instanceof Coins) {
            collisionEvent.getReportingBody().destroy();
        }
        if (collisionEvent.getOtherBody() instanceof Hearts) {
            collisionEvent.getReportingBody().destroy();
        }
    }
}
