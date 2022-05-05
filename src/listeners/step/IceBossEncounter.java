package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import dynamicBody.Player;
import dynamicBody.enemies.IceBoss;
import dynamicBody.enemies.WalkEnemy;
import game.GameLevel;
import game.GameView;
import game.Level1;
import org.jbox2d.common.Vec2;

public class IceBossEncounter implements StepListener {
    private final GameLevel level;
    private final IceBoss enemy;
    private final Player player;
    private int n;
    private final boolean restart = false;

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
        else if(enemy.getPosition().x >24)
        {
            enemy.startWalking(-8);
        }


        if (((enemy.getPosition().x -8)< player.getPosition().x)
                &&(player.getPosition().y<4
                &&(enemy.isAlive()))){

            player.setLinearVelocity(new Vec2(-30, 35));
            player.setPlayerHealth(player.getPlayerHealth()-1);
            enemy.removeAllImages();
            enemy.addImage(swipe1);

        }
/*
        System.out.println(player.getPosition());
*/
        if (n==100){
            n=0;
        }
        if (n==50){
            enemy.removeAllImages();
            enemy.addImage(boss);
            n=0;
            }
        }



}


