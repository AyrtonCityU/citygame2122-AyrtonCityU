package dynamicBody.enemies;

import city.cs.engine.*;
import game.GameLevel;
import org.jbox2d.common.Vec2;

//Class to generate the IceBoss from level 2
public class IceBoss extends Walker {

    private static final Shape bossShape = new BoxShape(3f,5);
    private static final BodyImage boss =
            new BodyImage("data/iceboss.gif", 10f);
    private boolean alive = true;

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    private GameLevel level;

    public IceBoss(World world){
        super(world, bossShape);
        addImage(boss);
        setPosition(new Vec2(22,-6));

    }

}
