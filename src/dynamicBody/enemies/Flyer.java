package dynamicBody.enemies;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import dynamicBody.Player;
import game.GameLevel;
import listeners.step.FlyerTracker;
import org.jbox2d.common.Vec2;
import static java.lang.Math.sin;


public class Flyer extends Walker {
    private static final Shape walkerShape = new BoxShape(1.8f,1);
    private GameLevel level;
    public static int flyerHealth;
    public Player player;




    public void setFlyerHealth (int flyerHealth){
        Flyer.flyerHealth = flyerHealth;
        System.out.println("Your health is:" + flyerHealth);
    }
    public int getFlyerHealth() {
        return flyerHealth;}

    public boolean ice = false;

    public boolean isIce() {
        return ice;
    }

    public void setIce(boolean ice) {
        this.ice = ice;
    }

    public boolean isDino() {
        return dino;
    }

    public void setDino(boolean dino) {
        this.dino = dino;
    }

    public boolean dino = false;

    public boolean isLava() {
        return lava;
    }

    public void setLava(boolean lava) {
        this.lava = lava;
    }

    public boolean lava = false;

    public boolean isDinoFlip() {
        return dinoFlip;
    }

    public void setDinoFlip(boolean dinoFlip) {
        this.dinoFlip = dinoFlip;
    }

    public boolean dinoFlip = false;

    public boolean ufo = false;

    public boolean isUfo() {
        return ufo;
    }

    public void setUfo(boolean ufo) {
        this.ufo = ufo;
    }

    public boolean evil = false;

    public boolean isEvil() {
        return evil;
    }

    public void setEvil(boolean evil) {
        this.evil = evil;
    }



    private static final BodyImage image =
            new BodyImage("data/birdfly.gif", 4f);

    public Flyer(World world) {
        super(world, walkerShape);
        addImage(image);
        setGravityScale(0);
        setPosition(new Vec2(20,0));
        setLinearVelocity(new Vec2(-8,0));
        FlyerTracker ft = new FlyerTracker(level, this);
        world.addStepListener(ft);
        flyerHealth = 2;

    }

    public void LToR(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/birdflyflip.gif", 4.5f));
        f.setPosition(new Vec2(-20, 0));
        f.setLinearVelocity(new Vec2(8,0));

    }
    public void Ice(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/bbirdfly.gif", 4.5f));
        f.setPosition(new Vec2(20, 10));
        f.setLinearVelocity(new Vec2(-15,0));
        ice = true;

    }
    public void Lava(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/lbirdfly.gif", 4.5f));
        f.setPosition(new Vec2(20, 9));
        f.setLinearVelocity(new Vec2(-15,0));
        lava = true;

    }

    public void Dino(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/velociL.gif",6f));
        f.setPosition(new Vec2(40, 9));
        dino = true;
    }

    public void DinoLToR(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/velociR.gif", 6f));
        f.startWalking(15);
        f.setPosition(new Vec2(-40,9));
        dinoFlip = true;
        dino = true;
    }

    public void Ufo(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/UFO.gif", 4.5f));
        f.setPosition(new Vec2(20, 10));
        f.setLinearVelocity(new Vec2(-5,0));
        ufo = true;

    }

    public void Evil(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/evilShip.gif", 5f));
        f.setPosition(new Vec2(20, 10));
        f.setLinearVelocity(new Vec2(-5,0));
        evil = true;

    }


}