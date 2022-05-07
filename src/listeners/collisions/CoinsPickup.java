package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import dynamicBody.Player;
import dynamicBody.Projectile;
import items.Shotgun;
import staticBody.Coins;
import staticBody.Hearts;
import staticBody.ShotgunPickup;

//Coins and hearts collisions
public class CoinsPickup implements CollisionListener {
    private final Player player;
    public CoinsPickup (Player s){
        this.player = s;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coins) {
            Player.setCoins(Player.getCoinsCollected()+10); //Set players coins + 10 if they collect
            e.getOtherBody().destroy(); //Destroy the coin
        }
        if (e.getOtherBody() instanceof Hearts) {
            //If the player's health is less than 3, it will increase by 1 on collision
            if(player.getPlayerHealth()<3){
                player.setPlayerHealth(player.getPlayerHealth()+1);
            }
            e.getOtherBody().destroy();

        }
        if (e.getOtherBody() instanceof ShotgunPickup) {
            Player.getBackpack().addItem(new Shotgun(player)); //If the player touches the shotgun they pick it up
            e.getOtherBody().destroy();
        }
        if ((e.getOtherBody() instanceof Coins)&& e.getReportingBody() instanceof Projectile){
            e.getReportingBody().destroy(); //Projectiles destroy
        }
        if ((e.getOtherBody() instanceof Hearts)&& e.getReportingBody() instanceof Projectile){
            e.getReportingBody().destroy(); //Projectiles destroy
        }

    }
}
