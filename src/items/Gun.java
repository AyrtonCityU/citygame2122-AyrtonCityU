package items;

import city.cs.engine.BodyImage;
import dynamicBody.Player;
import org.jbox2d.common.Vec2;

//Gun item
public class Gun extends BackpackItem{
    public Gun(Player player) {
        super(player);
        image = new BodyImage("data/gun.png", 3f);
    }

    @Override
    public String getType() {
        return "Gun";
    }

    @Override
    public void operate() {
    }

    @Override
    public void putOn() {
        super.putOn();

    }
}
