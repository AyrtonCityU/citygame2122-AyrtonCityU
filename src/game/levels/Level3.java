package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dynamicBody.Player;
import dynamicBody.enemies.*;
import game.Game;
import game.GameLevel;
import items.Gun;
import items.JumpBoots;
import listeners.collisions.*;
import listeners.step.LavaFlyerTracker;
import org.jbox2d.common.Vec2;
import staticBody.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level3 extends GameLevel
        implements ActionListener {

    public Vec2 walkerPos1;
    public static LavaFlyer flyer1;
    public static Flyer flyer2;
    public static Flyer flyer3;
    public static Flyer flyer4;
    public static WalkEnemy dino1;
    public static WalkEnemy dino2;
    public boolean hasSpawn1 = false;
    public static Branch branch1;
    private final Timer timer;
    private int spawn = 0;
    public static Warning warning;
    public static WalkEnemy penguin1;
    public static Snowball snowball;
    public static Meteor meteor1;
    public static Meteor meteor2;
    public static DeathBall death;


    public Level3(Game game) {
        super(game);

        getPlayer().setPosition(new Vec2(-19, 4));

        Ground ground = new Ground(this);
        ground.setPosition(new Vec2(0f, -20.5f));
        ground.setFillColor(Color.blue);

        getPlayer().setGravityScale(12);

        // make the trucks
        Truck truck1 = new Truck(this);
        truck1.setPosition(new Vec2(-16f, -14f));
        Shape truckFront1Shape = new BoxShape(0.9f, 0.2f);
        StaticBody truckFront1 = new StaticBody(this, truckFront1Shape);
        truckFront1.setPosition(new Vec2(-9.5f, -12f));
        Shape truckFront1ShapeL = new BoxShape(1f, 0.2f);
        StaticBody truckFront1L = new StaticBody(this, truckFront1ShapeL);
        truckFront1L.setPosition(new Vec2(-7f, -14f));

        Truck truck2 = new Truck(this);
        truck2.setPosition(new Vec2(4f, -14f));
        Shape truckFront2Shape = new BoxShape(0.9f, 0.2f);
        StaticBody truckFront2 = new StaticBody(this, truckFront2Shape);
        truckFront2.setPosition(new Vec2(10f, -12f));
        Shape truckFront2ShapeL = new BoxShape(1f, 0.2f);
        StaticBody truckFront2L = new StaticBody(this, truckFront2ShapeL);
        truckFront2L.setPosition(new Vec2(13f, -14f));

        Truck truck3 = new Truck(this);
        truck3.setPosition(new Vec2(22f, -14f));


        CoinsPickup pickup = new CoinsPickup(getPlayer());
        getPlayer().addCollisionListener(pickup);
        EnemyEncounter attack = new EnemyEncounter(getPlayer());
        getPlayer().addCollisionListener(attack);
        FloorCollision fall = new FloorCollision(getPlayer());
        getPlayer().addCollisionListener(fall);

        Player.getBackpack().addItem(new Gun(getPlayer()));
        Player.getBackpack().addItem(new JumpBoots(getPlayer()));

        BranchCollision branchAttack = new BranchCollision(getPlayer());
        getPlayer().addCollisionListener(branchAttack);

        truck1.addCollisionListener(branchAttack);
        truck2.addCollisionListener(branchAttack);
        truck3.addCollisionListener(branchAttack);


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
        return Player.getCoinsCollected() > -1;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        spawn++;

        if (spawn == 100) {
            for (int i = 0; i < 3; i++) {
                flyer1 = new LavaFlyer(this);
                flyer1.Lava(flyer1);
                flyer1.setPosition(new Vec2(25 + i * 6, 10));
                LavaFlyerTracker lft = new LavaFlyerTracker(this, flyer1, getPlayer());
                addStepListener(lft);
            }
        }
        if (spawn == 120){
            Hearts heart1 = new Hearts(this);
            heart1.setPosition(new Vec2(20f, 0f));
        }
        if (spawn == 180) {
            dino1 = new WalkEnemy(this);
            dino1.Dino(dino1);
            dino1.setPosition(new Vec2(27, -7));

        }

        if (spawn == 220) {
            dino2 = new WalkEnemy(this);
            dino2.Dino(dino2);
            dino2.setPosition(new Vec2(27, -7));
        }
        if (spawn == 280) {
            warning = new Warning(this);
            warning.setPosition(new Vec2(18,10));
        }

        if (spawn == 300) {
            warning.destroy();
            meteor1 = new Meteor(this);
            MeteorCollision mc = new MeteorCollision(meteor1, getPlayer());
            meteor1.addCollisionListener(mc);
            meteor1.setPosition(new Vec2(19,30));
        }
        if (spawn == 330) {
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(20f, 0f));

        }
        if (spawn ==420){
            flyer2 = new Flyer(this);
            flyer2.Dino(flyer2);

        }
        if (spawn == 480) {
            flyer3 = new Flyer(this);
            flyer3.DinoLToR(flyer3);
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(20f, 0f));
        }
        if (spawn == 520) {
            warning = new Warning(this);
            warning.setPosition(new Vec2(-18,10));
        }
        if (spawn == 545) {
            warning.destroy();
            meteor2 = new Meteor(this);
            MeteorCollision mc = new MeteorCollision(meteor2, getPlayer());
            meteor2.addCollisionListener(mc);
            meteor2.setPosition(new Vec2(-16,30));
            Hearts heart1 = new Hearts(this);
            heart1.setPosition(new Vec2(20f, 0f));

        }


        if (spawn == 650) {
            for (int i = 0; i<5;i++){
                flyer1 = new LavaFlyer(this);
                flyer1.Lava(flyer1);
                flyer1.setPosition(new Vec2(16 + i*10, -2 + i*4));
                LavaFlyerTracker lft = new LavaFlyerTracker(this, flyer1, getPlayer());
                addStepListener(lft);
            }
            flyer4 = new Flyer(this);
            flyer4.Dino(flyer4);

        }
        if (spawn == 750){
            death = new DeathBall(this);
        }
        if (spawn == 950) {

            getFlag().removeAllImages();
            getFlag().setPosition(new Vec2(16, -5));
            getFlag().addImage(new BodyImage("data/shipRight.png", 7));
        }


    }


}


