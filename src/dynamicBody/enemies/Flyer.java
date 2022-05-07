package dynamicBody.enemies;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import dynamicBody.Player;
import game.GameLevel;
import listeners.step.FlyerTracker;
import org.jbox2d.common.Vec2;

//Flyer enemies
public class Flyer extends Walker {
    private static final Shape walkerShape = new BoxShape(1.8f,1);
    private static final BodyImage image =
            new BodyImage("data/birdfly.gif", 4f);
    private GameLevel level;
    public Player player;

    //Booleans for the different types of flyers
    public boolean ice = false;
    public boolean dino = false;
    public boolean lava = false;
    public boolean ufo = false;
    public boolean evil = false;
    public boolean dinoFlip = false;

    //Getters and setters for different types of flyer
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

    public boolean isLava() {
        return lava;
    }
    public void setLava(boolean lava) {
        this.lava = lava;
    }

    public boolean isDinoFlip() {
        return dinoFlip;
    }
    public void setDinoFlip(boolean dinoFlip) {
        this.dinoFlip = dinoFlip;
    }

    public boolean isUfo() {
        return ufo;
    }
    public void setUfo(boolean ufo) {
        this.ufo = ufo;
    }

    public boolean isEvil() {
        return evil;
    }
    public void setEvil(boolean evil) {
        this.evil = evil;
    }


    //Default flyer variables
    public Flyer(World world) {
        super(world, walkerShape);
        addImage(image);
        setGravityScale(0); //So can fly
        setPosition(new Vec2(20,0)); //Default starting position
        setLinearVelocity(new Vec2(-8,0));
        FlyerTracker ft = new FlyerTracker(level, this);
        world.addStepListener(ft);

    }

    //Flipped version of the default bird
    public void LToR(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/birdflyflip.gif", 4.5f));
        f.setPosition(new Vec2(-20, 0));
        f.setLinearVelocity(new Vec2(8,0));

    }

    //Ice version of the flyer
    public void Ice(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/bbirdfly.gif", 4.5f));
        f.setPosition(new Vec2(20, 10));
        f.setLinearVelocity(new Vec2(-15,0));
        ice = true;

    }

    //Lava flyer
    public void Lava(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/lbirdfly.gif", 4.5f));
        f.setPosition(new Vec2(20, 9));
        f.setLinearVelocity(new Vec2(-15,0));
        lava = true;

    }

    //Dino flyer from level 3
    public void Dino(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/velociL.gif",6f));
        f.setPosition(new Vec2(40, 9));
        dino = true;
    }

    //Dino flyer who flies from left to right
    public void DinoLToR(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/velociR.gif", 6f));
        f.startWalking(15);
        f.setPosition(new Vec2(-40,9));
        dinoFlip = true;
        dino = true;
    }

    //UFO flyer from final level
    public void Ufo(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/UFO.gif", 4.5f));
        f.setPosition(new Vec2(20, 10));
        f.setLinearVelocity(new Vec2(-5,0));
        ufo = true;

    }

    //Evil spaceship from final level
    public void Evil(Flyer f){
        f.removeAllImages();
        f.addImage(new BodyImage("data/evilShip.gif", 5f));
        f.setPosition(new Vec2(20, 10));
        f.setLinearVelocity(new Vec2(-5,0));
        evil = true;

    }


}