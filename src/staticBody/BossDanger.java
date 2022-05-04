package staticBody;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import dynamicBody.enemies.IceBoss;

public class BossDanger extends SolidFixture {

    private static final Shape bossDangerShape = new BoxShape(3.5f, 4f);

    public BossDanger(IceBoss iceBoss){
        super(iceBoss, bossDangerShape);
    }
}
