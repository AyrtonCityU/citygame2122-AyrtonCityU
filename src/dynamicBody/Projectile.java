package dynamicBody;

import city.cs.engine.*;

public class Projectile extends DynamicBody {

    public String direction;
    private Player player;

    private static final BodyImage left =
            new BodyImage("data/playerleft.png", 4.5f);

    private static final BodyImage right =
            new BodyImage("data/playerright.png", 4.5f);

    public Projectile(World w) {
        super(w);
    }


}
