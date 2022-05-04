package dynamicBody.enemies;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import game.GameLevel;
import listeners.step.EnemyTracker;
import org.jbox2d.common.Vec2;


public class WalkEnemy extends Walker {



    public boolean flip = false;
    public boolean peng = false;
    public static float ehealth = 2;
    public boolean ice = false;

    public boolean isPflip() {
        return pflip;
    }

    public void setPflip(boolean pflip) {
        this.pflip = pflip;
    }

    public boolean pflip = false;

    public boolean isDino() {
        return dino;
    }

    public void setDino(boolean dino) {
        this.dino = dino;
    }

    public boolean dino = false;




    private static final Shape gorillaShape = new BoxShape(1.8f,2);
    private static final Shape penguinShape = new BoxShape(1f, 2);

    private static final BodyImage gorilla =
            new BodyImage("data/gorilla.gif", 4.5f);

    private GameLevel level;

    public WalkEnemy(World world) {
        super(world, gorillaShape);
        addImage(gorilla);
        startWalking(-8);
        EnemyTracker et = new EnemyTracker(level, this);
        world.addStepListener(et);
        ehealth = 1;

    }

    public void GFlip(WalkEnemy w){
        w.removeAllImages();
        w.addImage(new BodyImage("data/gorillaflip.gif", 4.5f));
        w.setPosition(new Vec2(-15, -7 ));
        startWalking(8);
        flip = true;

    }

    public void Penguin(WalkEnemy w){
        w.removeAllImages();
        w.addImage(new BodyImage("data/pengleft.png", 6f));
        peng=true;
        ice = true;
        ehealth = 2;
    }

    public void fPenguin(WalkEnemy w){
        w.removeAllImages();
        w.addImage(new BodyImage("data/pengright.png", 6f));
        w.setPosition(new Vec2(-15, -7));
        w.startWalking(8);
        ice = true;
        pflip = true;
        flip = true;

    }

    public void Dino(WalkEnemy wa){
        wa.removeAllImages();
        wa.addImage(new BodyImage("data/velociL2R.gif", 5f));
        wa.startWalking(-15);
        dino = true;

    }


    public float getEhealth() {
        return ehealth;
    }

    public void setEhealth(int ehealth) {
        WalkEnemy.ehealth = ehealth;
    }

    public boolean isFlip() {
        return flip;
    }

    public boolean isPeng() {
        return peng;
    }


}