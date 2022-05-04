package staticBody;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TruckFront extends StaticBody {
    private static final Shape truckfShape = new BoxShape(1f,2.3f);

    public TruckFront(World world) {
        super(world, truckfShape);
    }

}