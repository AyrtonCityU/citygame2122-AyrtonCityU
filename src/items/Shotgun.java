package items;


import city.cs.engine.BodyImage;
import dynamicBody.Player;
import org.jbox2d.common.Vec2;

//Shotgun item
public class Shotgun extends BackpackItem {

    public Shotgun(Player player) {
        super(player);

        image = new BodyImage("data/boots.png", 1.5f);
    }

    @Override
    public String getType() {
        return "Shotgun";
    }

    @Override
    public void operate() {

    }

    @Override
    public void putOn() {
        super.putOn();
    }


}