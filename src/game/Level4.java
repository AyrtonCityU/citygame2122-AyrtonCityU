package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dynamicBody.Projectile;
import dynamicBody.enemies.*;
import items.Gun;
import items.JumpBoots;
import listeners.collisions.*;
import listeners.step.BossEncounter;
import listeners.step.IceBossEncounter;
import org.jbox2d.common.Vec2;
import staticBody.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level4 extends GameLevel
        implements ActionListener {

    private static int spawn;
    public Vec2 walkerPos1;
    public static Flyer flyer1;
    public static Flyer flyer2;
    public static Flyer flyer3;
    public boolean hasSpawn1 = false;


    public static boolean bossSpawn = false;

    public static boolean isBossSpawn() {
        return bossSpawn;
    }

    public void setBossSpawn(boolean bossSpawn) {
        Level4.bossSpawn = bossSpawn;
    }

    private final Timer timer;

    public static int getSpawn() {
        return spawn;
    }

    public void setSpawn(int spawn) {
        this.spawn = spawn;
    }

    public static Warning warning;
    public static FinalBoss boss;

    /*private final JFrame frame;
    private final GameView view;*/


    public Level4(Game game) {
        super(game);

        getPlayer().setPosition(new Vec2(-19, 4));
        Ground ground = new Ground(this);
        ground.setPosition(new Vec2(0f, -20.5f));
        ground.setFillColor(Color.blue);

        // make the trucks



        CoinsPickup pickup = new CoinsPickup(getPlayer());
        getPlayer().addCollisionListener(pickup);
        EnemyEncounter attack = new EnemyEncounter(getPlayer());
        getPlayer().addCollisionListener(attack);
        FloorCollision fall = new FloorCollision(getPlayer());
        getPlayer().addCollisionListener(fall);

        getPlayer().getBackpack().addItem(new Gun(getPlayer()));
        getPlayer().getBackpack().addItem(new JumpBoots(getPlayer()));

        BranchCollision branchAttack = new BranchCollision(getPlayer());
        getPlayer().addCollisionListener(branchAttack);

        ProjectileCollision projectileCollision2 = new ProjectileCollision(getPlayer());
        getPlayer().addCollisionListener(projectileCollision2);

/*
        getPlayer().setAlwaysOutline(true);
*/

        Shape platformShape2 = new BoxShape(100f, 0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(128f, -8f));
        platform2.setFillColor(Color.green);


        /*for (int i = 0; i<5;i++){
            flyer1 = new LavaFlyer(this);
            flyer1.Lava(flyer1);
            flyer1.setPosition(new Vec2(16 + i*10, -2 + i*4));
            LavaFlyerTracker lft = new LavaFlyerTracker(this, flyer1, getPlayer());
            addStepListener(lft);
        }*/


       /*dino1 = new WalkEnemy(this);
        dino1.Dino(dino1);
        dino1.setPosition(new Vec2(27, -7));*/

        /*flyer4 = new Flyer(this);
        flyer4.Dino(flyer4);*/

        /*meteor1 = new Meteor(this);
        MeteorCollision mc = new MeteorCollision(meteor1, getPlayer());
        meteor1.addCollisionListener(mc);
        meteor1.setAlwaysOutline(false);*/


        timer = new Timer(50, this);
        timer.start();


    }

    @Override
    public boolean isComplete() {
        return getPlayer().getCoinsCollected() > 0;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        spawn++;
        if (FinalBoss.getBossHp()==0){
            getFlag().setPosition(new Vec2(1, -7));
        }
       /* if (spawn == 100) {
            for (int i = 0; i < 6; i++) {
                flyer1 = new Flyer(this);
                flyer1.Ufo(flyer1);
                flyer1.setPosition(new Vec2(25 + i * 9, 10));
            }
        }

        if (spawn == 450) {
           for (int i = 0; i<4; i++){
               flyer1 = new Flyer(this);
               flyer1.Evil(flyer1);
               flyer1.setPosition(new Vec2(30, -10 + i*7));
           }

        }*/

        if(spawn ==50){

            bossSpawn = true;

            /*view= new GameView(this, 500, 200);
            Game.frame.add(view);*/
            boss = new FinalBoss(this);
            boss.setGravityScale(0);
            boss.setPosition(new Vec2(5,-6));

            boss.setAlwaysOutline(false);

            BossEncounter be = new BossEncounter(this, boss, getPlayer());
            addStepListener(be);
            boss.setIdle(true);
        }
        if(spawn ==150){
            boss.setPosition(new Vec2(18,18));
            boss.setIdle(false);
            boss.setGrab(true);
        }
        if(spawn == 300){
            boss.setPunch(true);
        }

        if(spawn == 500){
            boss.setShoot(true);
        }


    }


}


