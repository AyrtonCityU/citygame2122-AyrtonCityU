package dynamicBody.enemies;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

//Flying tree from level 1
public class TreeFlying extends Walker {
    private static final Shape treeShape = new BoxShape(8, 4);
    private static final BodyImage image =
            new BodyImage("data/treefly.png", 8);

    public TreeFlying(World world) {
        super(world, treeShape);
        addImage(image);
        setLinearVelocity(new Vec2(-40, -5)); //It's fast!
        setGravityScale(0);
    }
}

