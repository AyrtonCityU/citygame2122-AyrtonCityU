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
import listeners.step.BossEncounter;
import listeners.step.Tracker;
import org.jbox2d.common.Vec2;
import staticBody.*;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Level4 extends GameLevel
        implements ActionListener {

    public static int spawn;
    public Vec2 walkerPos1;
    public static Flyer flyer1;
    public static Flyer flyer2;
    public static Flyer flyer3;
    public boolean hasSpawn1 = false;
    private static SoundClip bossTheme;


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

 /*   public void setSpawn(int spawn) {
        this.spawn = spawn;
    }*/

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

        Player.getBackpack().addItem(new Gun(getPlayer()));

        BranchCollision branchAttack = new BranchCollision(getPlayer());
        getPlayer().addCollisionListener(branchAttack);



/*
        getPlayer().setAlwaysOutline(true);
*/

        Shape platformShape2 = new BoxShape(100f, 0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(128f, -8f));
        platform2.setFillColor(Color.green);




        timer = new Timer(50, this);
        timer.start();


    }

    @Override
    public boolean isComplete() {
        return Player.getCoinsCollected() > 0;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        spawn++;
        if (FinalBoss.getBossHp()==0){
            getFlag().setPosition(new Vec2(1, -7));
        }
        if (spawn == 50 ){
            for (int i =0; i<31;i = i+5){
                flyer1 = new Flyer(this);
                flyer1.Ice(flyer1);
                flyer1.removeAllImages();
                flyer1.addImage(new BodyImage("data/bossGrab.gif", 4));
                flyer1.setPosition(new Vec2(30+i, i/3));
            }

        }
        if (spawn ==175){
             Hearts heart1 = new Hearts(this);
             heart1.setPosition(new Vec2(20f, 0f));

        }
        if (spawn == 200) {
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

        }
        if (spawn == 650) {
            for (int i = 0; i < 6; i++) {
                flyer1 = new Flyer(this);
                flyer1.Ufo(flyer1);
                flyer1.setPosition(new Vec2(25 + i * 9, 10));
            }

        }
        if (spawn == 720){
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(20f, 0f));
        }
        if (spawn == 860 ){
            for (int i =0; i<31;i = i+5){
                flyer1 = new Flyer(this);
                flyer1.Ice(flyer1);
                flyer1.removeAllImages();
                flyer1.addImage(new BodyImage("data/bossGrab.gif", 4));
                flyer1.setPosition(new Vec2(30+i, i/3));
            }
            Coins coin1 = new Coins(this);
            coin1.setPosition(new Vec2(20f, 10f));
        }

        if(spawn ==1000){
            Game.gameMusic.pause();
            try {
               bossTheme= new SoundClip("data/finalTheme.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
            bossTheme.play();

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
        if(spawn ==1100){
            boss.setPosition(new Vec2(18,18));
            boss.setIdle(false);
            boss.setGrab(true);
        }
        if(spawn == 1200){
            boss.setPunch(true);
        }

        if(spawn == 1600){
            boss.setShoot(true);
        }
        if(spawn == 1750){
            boss.setShoot(true);
        }
        if(spawn == 1800){
            boss.setShoot(true);
        }
        if(spawn ==1900){
            boss.setPosition(new Vec2(18,18));
            boss.setIdle(false);
            boss.setGrab(true);
        }
        if(spawn == 2200){
            boss.setPunch(true);
        }

        if(spawn == 2400){
            boss.setShoot(true);
        }
        if(spawn == 2450){
            boss.setShoot(true);
        }
        if(spawn == 2500){
            boss.setShoot(true);
        }


    }


}


