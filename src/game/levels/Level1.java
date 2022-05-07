package game.levels;

import city.cs.engine.*;
import city.cs.engine.StepListener;
import city.cs.engine.StepEvent;
import city.cs.engine.Shape;
import dynamicBody.Player;
import dynamicBody.enemies.Branch;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.TreeFlying;
import dynamicBody.enemies.WalkEnemy;
import game.Game;
import game.GameLevel;
import items.Gun;
import items.JumpBoots;
import items.Shotgun;
import listeners.collisions.BranchCollision;
import listeners.collisions.CoinsPickup;
import listeners.collisions.EnemyEncounter;
import listeners.collisions.FloorCollision;
import listeners.step.EnemyTracker;
import listeners.step.FlyerTracker;
import org.jbox2d.common.Vec2;
import staticBody.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.random;
import static java.lang.Math.sin;

public class Level1  extends GameLevel
                implements ActionListener {

    public static WalkEnemy walker1;
    public static WalkEnemy walker2;
    public static WalkEnemy walker3;
    public static WalkEnemy walker4;
    public Vec2 walkerPos1;
    public static Flyer flyer1;
    public static Flyer flyer2;
    public static Flyer flyer3;
    public static Flyer flyer4;
    public static Flyer flyer5;
    public static Flyer flyer6;
    public boolean hasSpawn1 = false;
    public static Branch branch1;
    private final Timer timer;

    public static int getSpawn() {
        return spawn;
    }

    public void setSpawn(int spawn) {
        this.spawn = spawn;
    }

    public static int spawn = 0;
    public static Warning warning;


        public Level1(Game game){
            super(game);

            getPlayer().setPosition(new Vec2(-19, 4));

            Ground ground = new Ground(this);
            ground.setPosition(new Vec2(0f, -20.5f));
            ground.setFillColor(Color.blue);

            getPlayer().setGravityScale(10);

            // make the trucks
            Truck truck1 = new Truck(this);
            truck1.setPosition(new Vec2(-16f, -14f));
            truck1.setFillColor(Color.blue);
            Shape truckFront1Shape = new BoxShape(0.9f, 0.2f);
            StaticBody truckFront1 = new StaticBody(this, truckFront1Shape);
            truckFront1.setPosition(new Vec2(-9.5f, -12f));
            Shape truckFront1ShapeL = new BoxShape(1f, 0.2f);
            StaticBody truckFront1L = new StaticBody(this, truckFront1ShapeL);
            truckFront1L.setPosition(new Vec2(-7f, -14f));


            //TruckFront truckf1 = new TruckFront(this);
            //truckf1.setPosition(new Vec2(0f,0f));
            //truckf1.setAlwaysOutline(true);



            Truck truck2 = new Truck(this);
            truck2.setPosition(new Vec2(4f, -14f));
            truck2.setFillColor(Color.blue);
            Shape truckFront2Shape = new BoxShape(0.9f, 0.2f);
            StaticBody truckFront2 = new StaticBody(this, truckFront2Shape);
            truckFront2.setPosition(new Vec2(9.5f, -12f));
            Shape truckFront2ShapeL = new BoxShape(1f, 0.2f);
            StaticBody truckFront2L = new StaticBody(this, truckFront2ShapeL);
            truckFront2L.setPosition(new Vec2(14f, -14f));



            Truck truck3 = new Truck(this);
            truck3.setPosition(new Vec2(24.4f, -14f));
            truck3.setFillColor(Color.blue);



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

            truck1.addCollisionListener(branchAttack);
            truck2.addCollisionListener(branchAttack);
            truck3.addCollisionListener(branchAttack);





            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(35f, 0f));
            Coins coin2 = new Coins(this);
            coin2.setPosition(new Vec2(100f, 0f));
            Coins coin3 = new Coins(this);
            coin3.setPosition(new Vec2(150f, 0f));


            Shape platformShape2 = new BoxShape(100f, 0.5f);
            StaticBody platform2 = new StaticBody(this, platformShape2);
            platform2.setPosition(new Vec2(128f, -8f));
            platform2.setFillColor(Color.green);

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


        if (spawn==100) {
            System.out.println("Spawn timer 1!");

            flyer1 = new Flyer(this);
            flyer2 = new Flyer(this);
            flyer2.LToR(flyer2);
            walker1 = new WalkEnemy(this);
            //walker1.setAlwaysOutline(true);
            walker1.setPosition(new Vec2(45, -7));
            walker1.startWalking(-8);

            hasSpawn1 = true;


        }
        if (spawn==230 & hasSpawn1){
            warning = new Warning(this);
            warning.setPosition(new Vec2(0,10));
        }
        if (spawn==250 & hasSpawn1) {
            double random = Math.random() * 2 + 1;
            warning.destroy();
            for (int i = 0; i<8; i++ ) {
                branch1 = new Branch(this);
                branch1.setPosition(new Vec2(-15 + 5*i, (float) ((float) 100*sin(i))));
            }
            for (int i = 0; i<8; i++ ) {
                branch1 = new Branch(this);
                branch1.setPosition(new Vec2((float) (-8 + 4*random), (float) ((float) 100*sin(i))));
            }

            System.out.println("Spawn timer 2!");
        }
        if(spawn ==300){
            Hearts heart1 = new Hearts(this);
            heart1.setPosition(new Vec2(20f, 0f));
        }
        if (spawn == 400){
            ShotgunPickup shotgun = new ShotgunPickup(this);
            shotgun.setPosition(new Vec2(20f, -5f));
        }
        if(spawn==500){

            flyer3 = new Flyer(this);
            flyer3.setPosition(new Vec2(21, 0));
            flyer4 = new Flyer(this);
            flyer4.setPosition(new Vec2(23, 0));
            flyer5 = new Flyer(this);
            flyer5.setPosition(new Vec2(25, 0));


            System.out.println("Spawn timer 3!");
        }
        if (spawn == 600){
            walker2 = new WalkEnemy(this);
            walker2.GFlip(walker2);
            walker3 = new WalkEnemy(this);
            walker3.setPosition(new Vec2(45, -7));
        }
        if (spawn==720){
            warning = new Warning(this);
            warning.setPosition(new Vec2(16,0));
        }
        if(spawn==750){
            warning.destroy();

            TreeFlying tree = new TreeFlying(this);
            tree.setPosition(new Vec2(20, -5));
            tree.setAlwaysOutline(true);

            System.out.println("Spawn timer 4!");
        }
        if(spawn==720) {
            getFlag().setPosition(new Vec2(0,-7));
        }
    }

}

