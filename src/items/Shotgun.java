package items;


import city.cs.engine.BodyImage;
import dynamicBody.Player;
import org.jbox2d.common.Vec2;

public class Shotgun extends BackpackItem {
    int dj = 0;

    public static int getJ() {
        return 60;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int j = 40;

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
        aImage.setOffset(new Vec2(0,5.2f));
    }


}