package items;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import dynamicBody.Player;
import org.jbox2d.common.Vec2;

public abstract class BackpackItem {

    protected Player player;
    protected BodyImage image;

    protected AttachedImage aImage;

    public BackpackItem(Player player){
        this.player = player;
    }

    public void putOn(){
        aImage = player.addImage(image);

        if (player.getDirection().equals("left"))
            aImage.flipHorizontal();

    }

    public void takeOff(){
        player.removeAttachedImage(aImage);
    }
    public abstract String getType();

    public abstract void operate();
}
