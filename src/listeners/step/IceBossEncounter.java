package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import dynamicBody.Player;
import dynamicBody.enemies.IceBoss;
import game.GameLevel;
import org.jbox2d.common.Vec2;

//Ice Boss listener
public class IceBossEncounter implements StepListener {
    private final GameLevel level;
    private final IceBoss enemy;
    private final Player player;
    private int n;

    //Swipe animation and walking animation
    private static final BodyImage swipe1 =
            new BodyImage("data/bossSwipe2.png",10f);
    private static final BodyImage boss =
            new BodyImage("data/iceboss.gif", 10f);

    public IceBossEncounter(GameLevel level, IceBoss enemy, Player player) {
        this.level = level;
        this.enemy = enemy;
        this.player = player;
    }
    public void preStep(StepEvent e) {        //System.out.println(new Vec2(enemy.getPosition()));
    }

    public void postStep(StepEvent e) {
        n++;
       //first jump 3 to 2
        if (enemy.getPosition().x < 20 && enemy.getPosition().x >19)
        {
            enemy.jump(8);
        }
        else if(enemy.getPosition().x < 1 && enemy.getPosition().x >-1) {
            enemy.startWalking(8);
        }
        else if(enemy.getPosition().x >6 && enemy.getPosition().x <8)
        {
                enemy.jump(8);
        }
        else if(enemy.getPosition().x >24) //Turn around if at far right edge of screen
        {
            enemy.startWalking(-8);
        }


        if (((enemy.getPosition().x -8)< player.getPosition().x) //If the player is close to the boss
                &&(player.getPosition().y<4
                &&(enemy.isAlive()))){

            player.setLinearVelocity(new Vec2(-30, 35)); //Hit (set the players linvelocity) the player away
            player.setPlayerHealth(player.getPlayerHealth()-1); //Reduce their health
            enemy.removeAllImages();
            enemy.addImage(swipe1); //Swipe animation

        }

        if (n==100){
            n=0;
        }
        if (n==50){
            enemy.removeAllImages();
            enemy.addImage(boss);
            n=0; //This was a really basic timer I made to just check if the boss has swiped and add his idle image
            }
        }



}


