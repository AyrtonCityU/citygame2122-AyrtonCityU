package listeners.step;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import dynamicBody.enemies.WalkEnemy;
import game.GameLevel;

public class EnemyTracker implements StepListener {
    private final GameLevel level;
    private final WalkEnemy enemy;

    public EnemyTracker(GameLevel level, Walker enemy) {
        this.level = level;
        this.enemy = (WalkEnemy) enemy;
    }
    public void preStep(StepEvent e) {        //System.out.println(new Vec2(enemy.getPosition()));
    }
    public void postStep(StepEvent e) {

        //first jump 3 to 2
        if (enemy.getPosition().x < 21 && enemy.getPosition().x >19)
        {
            if (enemy.isDino()){
                enemy.jump(15);
            }
            enemy.jump(8);

            if(enemy.isPeng() && !enemy.isPflip()){
                enemy.rotate(0.1f);
            }
        }
        //second jump 2 to 1
        else if(enemy.getPosition().x < 1 && enemy.getPosition().x >-1) {
            enemy.jump(8);

        }
        //final jump 1 to off
        else if(enemy.getPosition().x < -19 && enemy.getPosition().x >-21) {
            enemy.jump(8);

        }
        //first jump (for L2R enemies) 1 to 2
        else if(enemy.getPosition().x >-11 && enemy.getPosition().x <-9)
        {
            if (enemy.isFlip()){
                enemy.jump(8);
                if (enemy.isPflip()){
                    enemy.rotate(-0.1f);
                }
            }

        }
        //second jump (for L2R enemies) 2 to 3
        else if(enemy.getPosition().x >6 && enemy.getPosition().x <8)
        {
            if (enemy.isFlip()){
                enemy.jump(8);
            }
        }
        else if(enemy.getPosition().x>3 && enemy.getPosition().x<4){
            if (enemy.isPflip()){
                enemy.startWalking(30);
            }
        }
        else if(enemy.getPosition().x >5 && enemy.getPosition().x <7)
        {
            if (enemy.isPeng()){
                enemy.startWalking(-30);
            }
        }

/*
        System.out.println(enemy.getPosition());
*/


            //for (int n = 0; n<1000;n++ ){
                //if(n== 100){
                   //enemy.setPosition(new Vec2(45, -7));
                //}}
            }
        }



