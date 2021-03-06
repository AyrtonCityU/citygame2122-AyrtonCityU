package staticBody;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Warning sign that appears throughout the game
public class Warning extends StaticBody {
    private static final Shape warningShape = new BoxShape(2f,2f);
    private static final BodyImage image =
            new BodyImage("data/warning.gif", 4);

    public Warning(World world) {
        super(world, warningShape);
        addImage(image);
    }

    //This big one is for the massive meteor in level 3
    public void BigWarning(Warning w){
        w.removeAllImages();
        w.addImage(new BodyImage("data/warning.gif", 10));
    }

}