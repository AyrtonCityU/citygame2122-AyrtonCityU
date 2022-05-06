package staticBody;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Flag extends StaticBody {
    private static final Shape flagShape = new BoxShape(1f,2f);

    private static final BodyImage image =
            new BodyImage("data/portal.gif", 4f);

    public Flag(World world) {
        super(world, flagShape);
        addImage(image);
    }

}