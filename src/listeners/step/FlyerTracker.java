package listeners.step;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import dynamicBody.Player;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.WalkEnemy;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class FlyerTracker implements StepListener {
    private final GameLevel level;
    private final Flyer flyer;
    public float amplitude = 3; //Amplitude of default bird movement
    public float frequency = 0.5F;
    private Player player;



    public FlyerTracker(GameLevel level, Flyer flyer) {
        this.level = level;
        this.flyer = flyer;
        this.player = player;
    }
    public void preStep(StepEvent e){
    }
    public void postStep(StepEvent e){

    //Different frequency and amplitude if Ice or UFO
    if (flyer.isIce()){
        frequency = 0.2f;
        amplitude = 4f;
    }

    if (flyer.isUfo()){
            frequency = 0.4f;
            amplitude = 7f;
        }

    //Evil flyer slowly moves left until x is <18, then it rapidly moves to the left
    if (flyer.isEvil()){
        if (flyer.getPosition().x >18){
            flyer.setLinearVelocity(new Vec2(-2, 0));
        }
        else if (flyer.getPosition().x <18){
            flyer.setLinearVelocity(new Vec2(-50, 0));
        }
    }


    //Following 3 lines are used to make the flyers follow a sine wave movement
    Vec2 pos = new Vec2(flyer.getPosition());
    float sin = (float) Math.sin(pos.x * frequency) * amplitude;
    pos.y = sin;

    //Only applied to non-dino/lava/evil flyers
    if ((!flyer.isLava())&&(!flyer.isDino())&&(!flyer.evil)){
        flyer.setPosition(pos);
    }

    //Dino flyers fly left and then quickly fly diagonally
    if(flyer.isDino()){
        if (!flyer.isDinoFlip()){
        if ((flyer.getPosition().x< 14)&&(flyer.getPosition().x>13)) {
            flyer.rotateDegrees(5);
            flyer.setLinearVelocity(new Vec2(-30, -10));
        }
        }
    }

    //Same idea but flipped
    if(flyer.isDinoFlip()){
        if((flyer.getPosition().x>-14)&&(flyer.getPosition().x<-13)){
            flyer.rotate(-0.10f);
            flyer.setLinearVelocity(new Vec2(30,-10));
        }
    }
    }
}
