package listeners.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.GameLevel;
import staticBody.Flag;

public class FlagEncounter implements CollisionListener {
    private final GameLevel level;
    private final Game game;
    public FlagEncounter(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }
    @Override
    public void collide(CollisionEvent e) {
        //If player touches the flag(portal) go to next level
        if (e.getOtherBody() instanceof Flag
                && level.isComplete()){
            game.goToNextLevel();
        }
    }
}