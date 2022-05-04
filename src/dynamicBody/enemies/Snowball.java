package dynamicBody.enemies;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

public class Snowball extends Walker {
    private static final Shape snowballShape = new BoxShape(0.5F, 0.5f);

    private static final BodyImage image =
            new BodyImage("data/snowball.png", 1f);

    public Snowball(World world) {
        super(world, snowballShape);
        addImage(image);
        setGravityScale(0.5f);
    }
}

