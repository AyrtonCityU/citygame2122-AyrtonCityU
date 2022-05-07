package dynamicBody.enemies;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

// Branch "enemy" from level one, simple box shape with branch image
// Left as walker instead of DynamicBody so easy to do collisions
public class Branch extends Walker  {
    private static final Shape branchShape = new BoxShape(1F, 1);

    private static final BodyImage image =
            new BodyImage("data/branch.png", 2f);

    public Branch(World world) {
        super(world, branchShape);
        addImage(image);
        setLinearVelocity(new Vec2(0, 0));
        setGravityScale(0.5f); //Falls slowly from the screen so player can dodge
    }
}

