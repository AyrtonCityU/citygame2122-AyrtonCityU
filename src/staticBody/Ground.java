package staticBody;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//This ground is important! It's underneath the trucks and if you touch it you die
public class Ground extends StaticBody {
    private static final Shape groundShape = new BoxShape(100f, 0.5f);
    public Ground(World world) {
        super(world, groundShape);
    }

}