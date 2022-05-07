package dynamicBody.enemies;

import city.cs.engine.World;
import dynamicBody.Player;
import game.GameLevel;
import listeners.step.LavaFlyerTracker;

//Lava flyer class, created for the purpose of step listeners that only work for this particular Flyer
public class LavaFlyer extends Flyer{

    public LavaFlyer(World world) {
        super(world);

    }
}
