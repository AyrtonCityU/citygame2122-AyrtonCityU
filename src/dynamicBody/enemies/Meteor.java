package dynamicBody.enemies;

import city.cs.engine.*;
import dynamicBody.Player;
import listeners.collisions.MeteorCollision;
import org.jbox2d.common.Vec2;

//Meteor from level 3
public class Meteor extends Walker {
    private static final Shape meteorShape = new BoxShape(5,7);
    private static final BodyImage image =
            new BodyImage("data/meteor.gif", 20);

    //Starts high up (y = 20) and very slowly falls due to 0.1f gravity scale
    public Meteor(World world) {
        super(world, meteorShape);
        addImage(image);
        setLinearVelocity(new Vec2(0,0));
        setPosition(new Vec2(3,20));
        setGravityScale(0.1f);


    }



}
