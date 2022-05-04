package listeners.mkb;

import city.cs.engine.SoundClip;
import dynamicBody.Player;
import dynamicBody.Projectile;
import game.GameView;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class DirectionalShooting implements MouseListener {

    private Player player;
    private final GameView view;
    private Projectile projectile;

    private static SoundClip shotSound;

    static {
        try {
            shotSound = new SoundClip("data/shot.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public DirectionalShooting(Player p, GameView v){
        player = p;
        view = v;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Vec2 worldPoint = view.viewToWorld(e.getPoint());
        if (player.getBackpack().j == "Gun") {
            shotSound.play();
            player.shoot(worldPoint);
        }
        //}
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void updatePlayer(Player newPlayer){
        player = newPlayer;
    }
}
