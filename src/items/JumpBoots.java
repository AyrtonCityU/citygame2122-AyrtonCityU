package items;

import city.cs.engine.BodyImage;
import dynamicBody.Player;
import org.jbox2d.common.Vec2;

//Jump Boots item
public class JumpBoots extends BackpackItem {

    public JumpBoots(Player player) {
        super(player);

        image = new BodyImage("data/boots.png", 1.5f);
    }

    @Override
    public String getType() {
        return "JumpBoots";
    }

    @Override
    public void operate() {
    }

    @Override
    public void putOn() {
        super.putOn();

    }


}
