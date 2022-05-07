package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.Projectile;
import dynamicBody.enemies.FinalBoss;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.Snowball;
import dynamicBody.enemies.WalkEnemy;
import game.GameLevel;
import staticBody.Coins;
import staticBody.Hearts;
import staticBody.Truck;

import static game.levels.Level4.boss;

//Collision Listener for boss
public class BossProjectileCollision implements CollisionListener {
    public Projectile projectile;
    public Player player;
    public FinalBoss finalboss;

    public BossProjectileCollision(FinalBoss f) {
        this.finalboss = f;
    }


    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Player) {
            collisionEvent.getReportingBody().destroy(); //Destroy boss projectile
            GameLevel.player.setPlayerHealth(GameLevel.player.getPlayerHealth()-1); //Reduce players health by 1
        }

    }
}