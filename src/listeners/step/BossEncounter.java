package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import dynamicBody.Player;
import dynamicBody.enemies.FinalBoss;
import game.GameLevel;
import game.levels.Level4;
import org.jbox2d.common.Vec2;

/**Final boss listener*/
public class BossEncounter implements StepListener {
    private final GameLevel level;
    private final FinalBoss boss;
    private final Player player;
    private int n;

    //Booleans for bosses states
    private boolean corner = false;
    private boolean idleD = false;
    private boolean idleU = false;
    private final boolean grabLeft = false;
    private boolean grabRight = false;
    private boolean animation1time = false;
    private boolean pstart = false;
    private boolean shot = true;
    private boolean bossReturn = false;
    /**Check to record animation 2 time*/
    public boolean animation2time = false;

    //Boss movement frequencies
    /**Amplitude of bossgrab sine wave*/
    public float amplitude = 10;
    /**Frequency of bossgrab sine wave*/
    public float frequency = 0.3F;

    //Ints used for animation timing
    /**used for getting the time of the first animation*/
    public int animation1 = 0;
    /**used for getting the time of the second animation*/
    public int animation2 = 0;

    //Many images
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

    /** Creates the BossEncounter steplistener*/
    public BossEncounter(GameLevel level, FinalBoss boss, Player player) {
        this.level = level;
        this.boss = boss;
        this.player = player;
    }
    /**Nothing before each step*/
    public void preStep(StepEvent e) {
    }
    /**Does the following after each step*/
    public void postStep(StepEvent e) {
        n++;

        //This puts the boss back into the main screen if he goes past x:25 (can happen if being shot)
        if (boss.getPosition().x>25){
            boss.setPosition(new Vec2(15,0));
            boss.setLinearVelocity(new Vec2(0,0));
        }
        if (boss.isIdle()) { //This code moves him up and down (the idle animation)
            if (boss.getPosition().x>16&&bossReturn){
                boss.removeAllImages();
                boss.addImage(bossIdle);
                bossReturn = false;
            }
            if (boss.getPosition().x < 18 && boss.getPosition().y < 19 && !corner) {
                boss.setPosition(new Vec2((float) (boss.getPosition().x + 0.1), (float) (boss.getPosition().y + 0.1)));

                if (boss.getPosition().x > 17) { //If boss past x:17, idle down is true and corner is true
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
            if (boss.getPosition().x > -20 && !grabRight) { //Left grab
                //Sine wave movement. Very similar to flyer movement
                Vec2 pos = new Vec2(boss.getPosition());
                float sin = (float) Math.sin(pos.x * frequency) * amplitude;
                pos.y = sin;
                boss.setLinearVelocity(new Vec2(-8, 0));
                boss.removeAllImages();
                boss.addImage(grab); //Grab image
                boss.setPosition(pos);
                if (boss.getPosition().x < -19) {
                    grabRight = true; //If boss is at left of screen, grabRight is true
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
                    boss.addImage(bossIdle); //Boss would have now grabbed left and right
                    boss.setIdle(true); //So the boss is now idle
                    boss.setGrab(false); //And setGrab is false
                    grabRight = false;
                }
            }
        }

        //Punch attack
        if (boss.isPunch()) {
            if (!pstart) { //Start of the punch
                boss.removeAllImages();
                boss.addImage(bossPunchStart); //Hand clenching animation to know punch attack is coming
                pstart = true;
            }
            if (Math.round(boss.getPosition().y) == Math.round(player.getPosition().y)) { //Round so doesn't have to be exact
                boss.removeAllImages();
                boss.addImage(punch);
                boss.setLinearVelocity(new Vec2(-25, 0)); //Quickly move to the left
                }
            if (boss.getPosition().x < -19) {
                boss.setLinearVelocity(new Vec2(15, 0)); //Quickly move to the right
                boss.removeAllImages();
                boss.addImage(punchR);//Right punching image
                bossReturn = true;
                boss.setPunch(false);
                boss.setIdle(true);



            }
        }

        //Shoot attack
        if (boss.isShoot()){
            boss.removeAllImages();
            boss.addImage(bossGun);
            if(!animation1time){
                animation1 = Level4.getSpawn();
                animation1time = true;
            }
            if((Level4.getSpawn()==animation1+14)&&shot){
                System.out.println(Level4.getSpawn());
                boss.bossShoot(boss, level); //Shoot 14 frames after the shoot animation begins
                shot = false;

            }

            if(Level4.getSpawn()==animation1+21) {
                boss.removeAllImages();
                boss.addImage(bossIdle);
                animation1time = false;
                boss.setShoot(false);
                shot = true; //This makes the boss only shoot once
            }

        }

        if (boss.isHurt()){
            boss.removeAllImages();
            boss.addImage(hurt); //Hurt animation if the boss is hit
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
                animation2time = false; //Remove the hurt animation after some time has passed
            }
        }
    }


}


