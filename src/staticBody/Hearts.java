package staticBody;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Hearts extends DynamicBody {
    private static final Shape heartShape = new BoxShape(1f,1f);

    private static final BodyImage image =
            new BodyImage("data/heartPickup.gif", 3f);

    private static SoundClip heartSound;

    static {
        try {
            heartSound = new SoundClip("data/heartSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public Hearts(World world) {
        super(world, heartShape);
        addImage(image);
        setGravityScale(0);
        setLinearVelocity(new Vec2(-10, 0));
    }

    @Override
    public void destroy()
    {
        heartSound.play();
        super.destroy();
    }

}