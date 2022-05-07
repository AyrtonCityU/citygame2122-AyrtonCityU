package staticBody;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Trucks used in levels 1-3
public class Truck extends StaticBody {
    private static final Shape truckShape = new BoxShape(5f,5.3f);
    private static final BodyImage image =
            new BodyImage("data/truck.png", 10.6f);
    private static final BodyImage iceImage =
            new BodyImage("data/itruck.png", 10.6f);

    //Default look of the trucks
    public Truck(World world) {
        super(world, truckShape);
        addImage(image).setOffset(new Vec2(2.6f,0));
    }

    //Ice version of the trucks
    public void Ice(Truck t){
        t.removeAllImages();
        t.addImage(iceImage).setOffset(new Vec2(2.6f,0));
    }
}

