package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import dynamicBody.Player;
import dynamicBody.enemies.FinalBoss;
import game.GameLevel;
import game.levels.Level4;
import org.jbox2d.common.Vec2;

public class BossEncounter implements StepListener {
    private  GameLevel level;
    private FinalBoss boss;
    private  Player player;
    private int n;
    private boolean corner = false;
    private boolean idleD = false;
    private boolean idleU = false;
    private boolean grabLeft = false;
    private boolean grabRight = false;
    private boolean animation1time = false;
    private boolean pstart = false;
    private boolean shot = true;
    private boolean bossReturn = false;
    public float amplitude = 10;
    public float frequency = 0.3F;
    public int animation1 = 0;
    public int animation2 = 0;
    public boolean animation2time = false;

    private final boolean restart = false;

    private static final BodyImage grab =
            new BodyImage("data/bossGrab.gif",12f);

    private static final BodyImage grabR =
            new BodyImage("data/bossGrabR.gif",12f);

    private static final BodyImage bossIdle =
            new BodyImage("data/bossIdle.gif", 12f);

    private static final BodyImage bossPunchStart =
            new BodyImage("data/bossPunchStart.gif", 12f);

    private static final BodyImage punch =
            new BodyImage("data/bossPunchGo.gif", 12f);

    private static final BodyImage punchR =
            new BodyImage("data/bossPunchGoR.gif", 12f);

    private static final BodyImage bossGun =
            new BodyImage("data/bossShoot.gif", 12f);

    private static final BodyImage hurt =
            new BodyImage("data/bossHurt.gif", 12f);


    public BossEncounter(GameLevel level, FinalBoss boss, Player player) {
        this.level = level;
        this.boss = boss;
        this.player = player;
    }
    public void preStep(StepEvent e) {        //System.out.println(new Vec2(enemy.getPosition()));
    }

    public void postStep(StepEvent e) {
        n++;
        if (boss.getPosition().x>25){
            boss.setPosition(new Vec2(15,0));
            boss.setLinearVelocity(new Vec2(0,0));
        }
        if (boss.isIdle()) {
            if (boss.getPosition().x>16&&bossReturn){
                boss.removeAllImages();
                boss.addImage(bossIdle);
                bossReturn = false;
            }
            if (boss.getPosition().x < 18 && boss.getPosition().y < 19 && !corner) {
                boss.setPosition(new Vec2((float) (boss.getPosition().x + 0.1), (float) (boss.getPosition().y + 0.1)));

                if (boss.getPosition().x > 17) {
                    idleD = true;
                    corner = true;
                }
            }
            if (corner && idleD) {
                boss.setPosition(new Vec2(boss.getPosition().x, (float) (boss.getPosition().y - 0.1)));
                if (boss.getPosition().y < -8) {
                    idleU = true;
                    idleD = false;
                }
            } else if (corner && idleU) {
                boss.setPosition(new Vec2(boss.getPosition().x, (float) (boss.getPosition().y + 0.1)));
                if (boss.getPosition().y > 8) {
                    idleU = false;
                    idleD = true;
                }
            }
        }


        //GRAB ATTACK
        if (boss.isGrab()) {
            if (boss.getPosition().x > -20 && !grabRight) {
                Vec2 pos = new Vec2(boss.getPosition());
                float sin = (float) Math.sin(pos.x * frequency) * amplitude;
                pos.y = sin;
                boss.setLinearVelocity(new Vec2(-8, 0));
                boss.removeAllImages();
                boss.addImage(grab);
                boss.setPosition(pos);
                System.out.println("you got here");
                if (boss.getPosition().x < -19) {
                    System.out.println("go right");
                    grabRight = true;
                }
            }
            if (boss.getPosition().x < 17 && grabRight) {
                Vec2 pos = new Vec2(boss.getPosition());
                float sin = (float) Math.sin(pos.x * frequency) * amplitude;
                pos.y = sin;
                boss.setLinearVelocity(new Vec2(8, 0));
                boss.removeAllImages();
                boss.addImage(grabR);
                boss.setPosition(pos);
                if (boss.getPosition().x > 16) {
                    boss.removeAllImages();
                    boss.addImage(bossIdle);
                    boss.setIdle(true);
                    boss.setGrab(false);
                    grabRight = false;
                }
            }
        }

        if (boss.isPunch()) {
            if (!pstart) {
                boss.removeAllImages();
                boss.addImage(bossPunchStart);
                pstart = true;
            }
            if (Math.round(boss.getPosition().y) == Math.round(player.getPosition().y)) {
                boss.removeAllImages();
                boss.addImage(punch);
                boss.setLinearVelocity(new Vec2(-25, 0));
                }
            if (boss.getPosition().x < -19) {
                boss.setLinearVelocity(new Vec2(15, 0));
                boss.removeAllImages();
                boss.addImage(punchR);
                bossReturn = true;
                boss.setPunch(false);
                boss.setIdle(true);



            }
        }

        if (boss.isShoot()){
            boss.removeAllImages();
            boss.addImage(bossGun);
            if(!animation1time){
                animation1 = Level4.getSpawn();
                animation1time = true;
            }
            if((Level4.getSpawn()==animation1+14)&&shot){
                System.out.println(Level4.getSpawn());
                boss.bossShoot(boss, level);
                shot = false;

            }

            if(Level4.getSpawn()==animation1+21) {
                boss.removeAllImages();
                boss.addImage(bossIdle);
                animation1time = false;
                boss.setShoot(false);
                shot = true;
            }

        }

        if (boss.isHurt()){
            boss.removeAllImages();
            boss.addImage(hurt);
        }
/*
        System.out.println(boss.getPosition());
*/
        if(boss.isHurt()){
            if(!animation2time){
                animation2 = Level4.getSpawn();
                animation2time = true;
            }
            if (Level4.getSpawn() == animation2 +10){
                boss.setHurt(false);
                boss.removeAllImages();
                boss.addImage(bossIdle);
                animation2time = false;
            }
        }
    }


}


