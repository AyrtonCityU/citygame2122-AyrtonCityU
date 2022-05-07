package dynamicBody;

import city.cs.engine.*;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//Projectiles fired by the player when a ship in final level
public class Projectile extends DynamicBody {

    private SoundClip projSound; //Firing sound

    public Projectile(World w, Player player) {
        super(w);

        try {
            projSound = new SoundClip("data/laser.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        projSound.play();

        //Creating the projectile
        DynamicBody projectile = new DynamicBody(this.getWorld(), new BoxShape(2, 1));
        ProjectileCollision projectileCollision = new ProjectileCollision(player);
        projectile.addCollisionListener(projectileCollision);
        projectile.addImage(new BodyImage("data/shipshot.gif", 3f));
        projectile.setGravityScale(0);
        //A bit in front of the player so it appears to shoot from front of ship
        projectile.setPosition(new Vec2(player.getPosition().x + 5, player.getPosition().y));
        //Can give it a fixed LinearVelocity Vec2 because can only fire right in final level
        projectile.setLinearVelocity(new Vec2(30, 0));
    }


}
