package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dynamicBody.enemies.*;
import game.Game;
import game.GameLevel;
import items.Gun;
import items.JumpBoots;
import listeners.collisions.BranchCollision;
import listeners.collisions.CoinsPickup;
import listeners.collisions.EnemyEncounter;
import listeners.collisions.FloorCollision;
import listeners.step.IceBossEncounter;
import org.jbox2d.common.Vec2;
import staticBody.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level2 extends GameLevel
        implements ActionListener {

    public Vec2 walkerPos1;
    public static Flyer flyer1;
    public static Flyer flyer2;
    public static IceBoss iceBoss;
    public boolean hasSpawn1 = false;
    public static Branch branch1;
    private final Timer timer;
    private int spawn = 0;
    public static Warning warning;
    public static Warning bwarning;
    public static WalkEnemy penguin1;
    public static WalkEnemy penguin2;
    public static WalkEnemy penguin3;
    public static WalkEnemy penguin4;
    public static WalkEnemy penguin5;

    public static Snowball snowball;


    public Level2(Game game) {
        super(game);

        getPlayer().setPosition(new Vec2(-19, 4));

        Ground ground = new Ground(this);
        ground.setPosition(new Vec2(0f, -20.5f));
        ground.setFillColor(Color.blue);

        getPlayer().setGravityScale(10);

        // make the trucks
        Truck truck1 = new Truck(this);
        truck1.Ice(truck1);
        truck1.setPosition(new Vec2(-16f, -14f));
        Shape truckFront1Shape = new BoxShape(0.9f, 0.2f);
        StaticBody truckFront1 = new StaticBody(this, truckFront1Shape);
        truckFront1.setPosition(new Vec2(-9.5f, -12f));
        Shape truckFront1ShapeL = new BoxShape(1f, 0.2f);
        StaticBody truckFront1L = new StaticBody(this, truckFront1ShapeL);
        truckFront1L.setPosition(new Vec2(-7f, -14f));

        Truck truck2 = new Truck(this);
        truck2.Ice(truck2);
        truck2.setPosition(new Vec2(4f, -14f));
        Shape truckFront2Shape = new BoxShape(0.9f, 0.2f);
        StaticBody truckFront2 = new StaticBody(this, truckFront2Shape);
        truckFront2.setPosition(new Vec2(9.5f, -12f));
        Shape truckFront2ShapeL = new BoxShape(1f, 0.2f);
        StaticBody truckFront2L = new StaticBody(this, truckFront2ShapeL);
        truckFront2L.setPosition(new Vec2(14f, -14f));


        Truck truck3 = new Truck(this);
        truck3.Ice(truck3);
        truck3.setPosition(new Vec2(22f, -14f));

        Shape slideShape = new BoxShape(6f, 5.4f);
        SolidFixture slide = new SolidFixture(truck1, slideShape);
        slide.setFriction(0);



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


        Shape platformShape2 = new BoxShape(100f, 0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(128f, -8f));
        platform2.setFillColor(Color.green);

        /*penguin1 = new WalkEnemy(this);
        penguin1.Penguin(penguin1);
        penguin1.setPosition(new Vec2(30, -7));
        penguin1.setAlwaysOutline(true);*/

       /* penguin1 = new WalkEnemy(this);
        penguin1.fPenguin(penguin1);
        penguin1.setAlwaysOutline(true);*/

        /*flyer1 = new Flyer(this);
        flyer1.Ice(flyer1);*/

        flyer1 = new Flyer(this);
        flyer1.Ice(flyer1);

        /*iceBoss = new IceBoss(this);
        iceBoss.setAlwaysOutline(true);
        new BossDanger(iceBoss);
        iceBoss.startWalking(-8);
        *//*Shape bossDangerShape = new BoxShape(3.5f, 4f);
        SolidFixture bossDanger = new SolidFixture(iceBoss, bossDangerShape);*//*
        BossEncounter be = new BossEncounter(this, iceBoss, getPlayer());
        addStepListener(be);*/

      /*  for (int i =0; i<8;i++){
            snowball = new Snowball(this);
            snowball.setLinearVelocity(new Vec2(-30, (float) (Math.random()*i)));
            snowball.setPosition(new Vec2((float) (Math.random()*25)+25,i*2 - 3));
            snowball.setAlwaysOutline(true);
        }*/

        Coins coin1 = new Coins(this);
        coin1.setPosition(new Vec2(20f, 0f));

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
            flyer1 = new Flyer(this);
            flyer1.Ice(flyer1);
            flyer2 = new Flyer(this);
            flyer2.Ice(flyer2);
            flyer2.setPosition(new Vec2(35, 5));
            penguin1 = new WalkEnemy(this);
            penguin1.Penguin(penguin1);
            penguin1.setPosition(new Vec2(30, -7));
            penguin2= new WalkEnemy(this);
            penguin2.Penguin(penguin2);
            penguin2.setPosition(new Vec2(45, -7));

        }
        if (spawn ==200){
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(35f, 0f));
        }
        if (spawn==230 ){
            warning = new Warning(this);
            warning.setPosition(new Vec2(15,0));
        }
        if (spawn==250) {
            warning.destroy();
            for (int i =0; i<8;i++){
                snowball = new Snowball(this);
                snowball.setLinearVelocity(new Vec2(-30, (float) (Math.random()*i)));
                snowball.setPosition(new Vec2((float) (Math.random()*25)+25,i*2 - 3));
            }

        }
        if (spawn == 300){
            Hearts heart1 = new Hearts(this);
            heart1.setPosition(new Vec2(20f, 0f));
        }
        if(spawn==350) {
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(20f, 0f));
            penguin4 = new WalkEnemy(this);
            penguin4.fPenguin(penguin4);
        }
        if (spawn==370){
            penguin5 = new WalkEnemy(this);
            penguin5.fPenguin(penguin5);
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(35f, 0f));
        }
        if (spawn == 420){
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(20f, 0f));
            for (int i =0; i<16;i = i+5){
                flyer1 = new Flyer(this);
                flyer1.Ice(flyer1);
                flyer1.setPosition(new Vec2(30+i, i/2));
            }
        }

        if (spawn==480){
            warning = new Warning(this);
            warning.setPosition(new Vec2(0,10));
        }
        if (spawn == 500){
            warning.destroy();
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(20f, 0f));
            for (int i =0; i<12;i++){
                snowball = new Snowball(this);
                snowball.setLinearVelocity(new Vec2(0, -5));
                snowball.setPosition(new Vec2((float) (Math.random()*25)-25,10+i*2));
            }
        }
        if (spawn == 560){
            Hearts heart1 = new Hearts(this);
            heart1.setPosition(new Vec2(20f, 0f));
        }
        if (spawn == 580){
            bwarning = new Warning(this);
            bwarning.setPosition(new Vec2(5,0));
            bwarning.BigWarning(bwarning);
        }
        if (spawn == 610){
            bwarning.destroy();

        }
        if (spawn == 630){
            iceBoss = new IceBoss(this);
            iceBoss.setAlwaysOutline(false);
            new BossDanger(iceBoss);
            Shape bossDangerShape = new BoxShape(3.5f, 4f);
            SolidFixture bossDanger = new SolidFixture(iceBoss, bossDangerShape);
            IceBossEncounter be = new IceBossEncounter(this, iceBoss, getPlayer());
            addStepListener(be);
            iceBoss.startWalking(-8);

        }


        if(spawn==650){

        }
        if(spawn==700) {
            getFlag().setPosition(new Vec2(0,-7));
        }
    }


}


