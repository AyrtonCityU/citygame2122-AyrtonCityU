package dynamicBody.enemies;

import city.cs.engine.*;
import dynamicBody.Player;
import listeners.collisions.MeteorCollision;
import org.jbox2d.common.Vec2;

public class DeathBall extends Walker {
    private static final Shape meteorShape = new BoxShape(40,5);
    private static final BodyImage image =
            new BodyImage("data/deathBall.gif", 50);

    public DeathBall(World world) {
        super(world, meteorShape);
        addImage(image);
        setLinearVelocity(new Vec2(2,0));
        setPosition(new Vec2(-70,-2));
        setGravityScale(0f);


    }



}