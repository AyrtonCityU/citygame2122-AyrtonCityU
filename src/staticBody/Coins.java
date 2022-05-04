package staticBody;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Coins extends DynamicBody {
    private static final Shape coinShape = new BoxShape(1f,1f);

    private static final BodyImage image =
            new BodyImage("data/coin.gif", 2f);

    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/coin.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public Coins(World world) {
        super(world, coinShape);
        addImage(image);
        setGravityScale(0);
        setLinearVelocity(new Vec2(-10, 0));
    }

    @Override
    public void destroy()
    {
        coinSound.play();
        super.destroy();
    }

}

