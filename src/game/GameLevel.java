package game;

import city.cs.engine.StepListener;
import city.cs.engine.World;
import dynamicBody.Player;
import listeners.collisions.FlagEncounter;
import listeners.mkb.DirectionalShooting;
import listeners.mkb.PlayerController;
import listeners.step.Tracker;
import org.jbox2d.common.Vec2;
import staticBody.Flag;

public abstract class GameLevel extends World {
    public static Player player;
    public Flag flag;
    public GameView view;
/*
    public static int getSpawn() {
        return spawn;
    }

    public static void setSpawn(int spawn) {
        GameLevel.spawn = spawn;
    }*/

/*
    public static int spawn;
*/

    public GameLevel(Game game){
        player = new Player(this);
        flag = new Flag(this);
        getFlag().setPosition(new Vec2(200, -10));
        player.addCollisionListener(new FlagEncounter(this, game));
        StepListener tracker = new Tracker(view, this.getPlayer());
        this.addStepListener(tracker);

    }

    public static Player getPlayer(){
        return player;
    }


    public Flag getFlag(){
        return flag;
    }
    public abstract boolean isComplete();
}