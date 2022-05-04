package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.Projectile;
import staticBody.Coins;

public class CoinsPickup implements CollisionListener {
    private final Player player;
    public CoinsPickup (Player s){
        this.player = s;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coins) {
            player.setCoins(player.getCoinsCollected()+10);
            e.getOtherBody().destroy();
        }
        if ((e.getOtherBody() instanceof Coins)&& e.getReportingBody() instanceof Projectile){
            e.getReportingBody().destroy();
        }

    }
}
