package items;

import city.cs.engine.BodyImage;
import dynamicBody.Player;
import org.jbox2d.common.Vec2;

public class JumpBoots extends BackpackItem {
    int dj = 0;

    public static int getJ() {
        return 60;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int j = 40;

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
        if (dj ==0){
            player.setLinearVelocity(new Vec2(0, 50));
            dj =1;
        }
        if((player.getPosition().y <0)){
            dj =0;
        }
    }

    @Override
    public void putOn() {
        super.putOn();
        j = 60;
    }


}
