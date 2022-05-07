package game;

import city.cs.engine.StepListener;
import city.cs.engine.World;
import dynamicBody.Player;
import listeners.collisions.FlagEncounter;

import listeners.step.Tracker;
import org.jbox2d.common.Vec2;
import staticBody.Flag;

//Default game level class
public abstract class GameLevel extends World {
    public static Player player;
    public Flag flag;
    public GameView view;

    public GameLevel(Game game){
        player = new Player(this);
        flag = new Flag(this);
        getFlag().setPosition(new Vec2(200, -10));
        player.addCollisionListener(new FlagEncounter(this, game));
        StepListener tracker = new Tracker(view, getPlayer());
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