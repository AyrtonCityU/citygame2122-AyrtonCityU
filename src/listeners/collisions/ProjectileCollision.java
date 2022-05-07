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
            player.setCoins(player.getCoinsCollected()+5);
        }
        if (collisionEvent.getOtherBody() instanceof Flyer) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
            player.setCoins(player.getCoinsCollected()+5);
        }
        if (collisionEvent.getOtherBody() instanceof Truck) {
            collisionEvent.getReportingBody().destroy();
        }
        if (collisionEvent.getOtherBody() instanceof Snowball) {
            collisionEvent.getOtherBody().destroy();
        }

        if(collisionEvent.getOtherBody() instanceof FinalBoss){
            boss.setHurt(true);
            collisionEvent.getReportingBody().destroy();
            FinalBoss.setBossHp(FinalBoss.getBossHp()-1);
            if (FinalBoss.getBossHp()==0){
                collisionEvent.getOtherBody().destroy();
                player.setCoins(player.getCoinsCollected()+100);
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
