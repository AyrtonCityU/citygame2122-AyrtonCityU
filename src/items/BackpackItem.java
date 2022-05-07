package items;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import dynamicBody.Player;
import org.jbox2d.common.Vec2;

//This gets the item type and puts it on/takes it off.
public abstract class BackpackItem {

    protected Player player;
    protected BodyImage image;

    public BackpackItem(Player player){
        this.player = player;
    }

    public void putOn(){
    }

    public void takeOff(){
    }
    public abstract String getType();

    public abstract void operate();
}
