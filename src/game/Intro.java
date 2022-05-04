package game;

import city.cs.engine.*;
import city.cs.engine.Shape;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Intro  extends GameLevel
        implements ActionListener {

    private int spawn;
    private final Timer timer;
    public boolean isIntro = true;
    private static Shape windowShape;
    private static final BodyImage image =
            new BodyImage("data/clustertruck.png", 20f);
    private Game game;

    public Intro(Game game) {
        super(game);
        windowShape = new BoxShape(10f,10f);
        StaticBody window = new StaticBody(this, windowShape);
        window.addImage(image);
        getPlayer().destroy();
        getFlag().destroy();



        timer = new Timer(50, this);
        timer.start();

        if (isComplete()&&isIntro()){
            game.goToNextLevel();
        }

    }

    @Override
    public boolean isComplete() {
            return true;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {

        spawn++;

        if (spawn > 100){
            isComplete();
            isIntro();
            }


    }

    public boolean isIntro() {
        return isIntro;
    }
}