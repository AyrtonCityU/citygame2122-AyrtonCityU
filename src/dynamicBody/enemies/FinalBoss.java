package dynamicBody.enemies;

import city.cs.engine.*;
import game.GameLevel;
import org.jbox2d.common.Vec2;

public class FinalBoss extends Walker {

    private static final Shape bossShape = new BoxShape(3f,5);

    private static final BodyImage boss =
            new BodyImage("data/bossIdle.gif", 10f);

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private boolean alive = true;

    private GameLevel level;

    public FinalBoss(World world){
        super(world, bossShape);
        addImage(boss);
        setPosition(new Vec2(5,-6));

    }

}
