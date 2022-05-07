package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import dynamicBody.Player;
import dynamicBody.enemies.Branch;
import dynamicBody.enemies.Flyer;
import dynamicBody.enemies.WalkEnemy;
import items.Gun;
import items.JumpBoots;
import listeners.collisions.BranchCollision;
import listeners.collisions.CoinsPickup;
import listeners.collisions.EnemyEncounter;
import listeners.collisions.FloorCollision;
import org.jbox2d.common.Vec2;
import staticBody.Coins;
import staticBody.Flag;
import staticBody.Ground;
import staticBody.Truck;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

import static java.lang.Math.sin;

public class GameWorld extends World
                        implements ActionListener{

    private final Player player;
    public static Walker walker1;
    public static Walker walker2;
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
    private int spawn = 0;


    public GameWorld() {
        super();

        Ground ground = new Ground(this);
        ground.setPosition(new Vec2(0f, -20.5f));
        ground.setFillColor(Color.blue);


        // make the trucks
        Truck truck1 = new Truck(this);
        truck1.setPosition(new Vec2(-16f, -14f));
        truck1.setFillColor(Color.blue);
        //truck1.setAlwaysOutline(true);

        Truck truck2 = new Truck(this);
        truck2.setPosition(new Vec2(4f, -14f));
        truck2.setFillColor(Color.blue);

        Truck truck3 = new Truck(this);
        truck3.setPosition(new Vec2(24.4f, -14f));
        truck3.setFillColor(Color.blue);

        //truck2.setAlwaysOutline(true);
        //truck3.setAlwaysOutline(true);



        // make the character
        player = new Player(this);
        player.setPosition(new Vec2(-19, 4));
        //player.setAlwaysOutline(true);
        player.setGravityScale(10);
        CoinsPickup pickup = new CoinsPickup(player);
        player.addCollisionListener(pickup);
        EnemyEncounter attack = new EnemyEncounter(player);
        player.addCollisionListener(attack);
        FloorCollision fall = new FloorCollision(player);
        player.addCollisionListener(fall);

        Player.getBackpack().addItem(new Gun(player));
        Player.getBackpack().addItem(new JumpBoots(player));

        BranchCollision branchAttack = new BranchCollision(player);
        player.addCollisionListener(branchAttack);

        truck1.addCollisionListener(branchAttack);
        truck2.addCollisionListener(branchAttack);
        truck3.addCollisionListener(branchAttack);



        walker1 = new WalkEnemy(this);
        //walker1.setAlwaysOutline(true);
        walker1.setPosition(new Vec2(45, -7));
        walker1.startWalking(-8);


        //flyer1 = new Flyer(this);
        //flyer1.setPosition(new Vec2(10, 0));
        //flyer1.setAlwaysOutline(true);
        //flyer2 = new Flyer(this);
        //flyer2.setPosition(new Vec2(30, -5));
        //flyer3 = new Flyer(this);
        //flyer3.setPosition(new Vec2(50, 0));


        Coins coin1 = new Coins(this);
        coin1.setPosition(new Vec2(0f, 0f));
        Coins coin2 = new Coins(this);
        coin2.setPosition(new Vec2(25f, 0f));
        Coins coin3 = new Coins(this);
        coin3.setPosition(new Vec2(45f, 0f));


        // make a platform
        //Shape platformShape = new BoxShape(3.5f, 0.5f);
        //StaticBody platform1 = new StaticBody(this, platformShape);
        //platform1.setPosition(new Vec2(-8, 5.5f));
        //platform1.setFillColor(Color.green);
        //platform1.setAngle(-45);

        Shape platformShape2 = new BoxShape(100f, 0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(128f, -8f));
        platform2.setFillColor(Color.green);

        Flag flag1 = new Flag(this);
        flag1.setPosition(new Vec2(0f,-5f));

        //ball
        //Shape ballShape = new CircleShape(1.5f);
        //DynamicBody ball1 = new DynamicBody(this, ballShape);
        //ball1.setPosition(new Vec2(-8f, 6f));
        //ball1.setFillColor(Color.red);

        timer = new Timer(5000, this);
        timer.start();

        }



    public Player getPlayer(){

        return player;
    }

    public Walker getWalker() {
        return walker1;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        spawn++;

        if (spawn==1 & !hasSpawn1) {
            flyer4 = new Flyer(this);
            flyer4.setPosition(new Vec2(5, 0));
            walker2 = new WalkEnemy(this);
            //walker2.setAlwaysOutline(true);
            walker2.setPosition(new Vec2(10, -7));
            hasSpawn1 = true;
        }
        if (spawn==2 & hasSpawn1) {
            flyer5 = new Flyer(this);
            flyer5.setPosition(new Vec2(5, 0));
        }
        if(spawn==3){
            flyer6 = new Flyer(this);
            flyer6.setPosition(new Vec2(5, 5));
            flyer6.setLinearVelocity(new Vec2( 20,5));
        }
        if(spawn==4){
            for (int i = 0; i<8; i++ ) {
                branch1 = new Branch(this);
                branch1.setPosition(new Vec2(-15 + 5*i, (float) ((float) 100*sin(i))));
            }
        }
    }
}
