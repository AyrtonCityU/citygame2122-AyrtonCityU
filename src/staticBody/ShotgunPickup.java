package staticBody;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//Same idea as coins and hearts class, just without the sounds
public class ShotgunPickup extends DynamicBody {
    private static final Shape shotgunShape = new BoxShape(1f,1f);
    private static final BodyImage image =
            new BodyImage("data/shotgun.png", 2f);



    public ShotgunPickup(World world) {
        super(world, shotgunShape);
        addImage(image);
        setGravityScale(0);
        setLinearVelocity(new Vec2(-10, 0));
    }

}