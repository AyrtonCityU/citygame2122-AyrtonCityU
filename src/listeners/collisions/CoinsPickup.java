package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.Projectile;
import items.Shotgun;
import staticBody.Coins;
import staticBody.Hearts;
import staticBody.ShotgunPickup;

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
        if (e.getOtherBody() instanceof Hearts) {
            if(player.getPlayerHealth()<3){
                player.setPlayerHealth(player.getPlayerHealth()+1);
            }
            e.getOtherBody().destroy();

        }
        if (e.getOtherBody() instanceof ShotgunPickup) {
            player.getBackpack().addItem(new Shotgun(player));
            e.getOtherBody().destroy();
        }
        if ((e.getOtherBody() instanceof Coins)&& e.getReportingBody() instanceof Projectile){
            e.getReportingBody().destroy();
        }

    }
}
