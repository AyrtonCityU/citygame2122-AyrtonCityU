package listeners.step;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import dynamicBody.enemies.FinalBoss;
import game.GameView;
import org.jbox2d.common.Vec2;

public class BossZoom implements StepListener {
    private GameView view;
    private FinalBoss boss;
    public BossZoom(GameView view, FinalBoss boss) {
        this.view = view;
        this.boss = boss;
    }

    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(boss.getPosition()));
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }
}