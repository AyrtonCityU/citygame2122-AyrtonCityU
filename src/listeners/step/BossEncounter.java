package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import dynamicBody.Player;
import dynamicBody.enemies.FinalBoss;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.IceBoss;
import dynamicBody.enemies.WalkEnemy;
import game.GameLevel;
import game.GameView;
import game.Level1;
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
    private boolean pstart = false;
    public float amplitude = 10;
    public float frequency = 0.3F;

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


    public BossEncounter(GameLevel level, FinalBoss boss, Player player) {
        this.level = level;
        this.boss = boss;
        this.player = player;
    }
    public void preStep(StepEvent e) {        //System.out.println(new Vec2(enemy.getPosition()));
    }

    public void postStep(StepEvent e) {
        n++;

        if (boss.isIdle()) {
            if (boss.getPosition().x < 18 && boss.getPosition().y < 19 && !corner) {
                boss.setPosition(new Vec2((float) (boss.getPosition().x + 0.1), (float) (boss.getPosition().y + 0.1)));
                boss.removeAllImages();
                boss.addImage(bossIdle);
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
            if (boss.getPosition().x < -10) {
                boss.setLinearVelocity(new Vec2(15, 0));
                boss.removeAllImages();
                boss.addImage(punchR);
                boss.setIdle(true);
                boss.setPunch(false);
            }
            System.out.println(boss.getPosition());
        }


    }


}

