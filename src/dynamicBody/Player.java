package dynamicBody;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import game.Game;
import game.NameInput;
import items.Backpack;
import listeners.collisions.ProjectileCollision;
import org.jbox2d.common.Vec2;

public class Player extends Walker {
    private Game game;
    private static final Shape playerShape = new PolygonShape(0.33f,2.23f,
            -0.16f,2.22f,
            -0.95f,1.89f,
            -1.27f,0.33f,
            -0.87f,-2.21f,
            1.07f,-2.22f,
            1.34f,0.5f,
            1.25f,1.87f);

    public static int playerHealth;

    public int coinsCollected;

    public void setCoins(int coinsCollected){
        this.coinsCollected = coinsCollected;
        System.out.println("Your score is: " + coinsCollected);
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public static boolean hasName;

    public  boolean isHasName() {
        return hasName;
    }

    public void setHasName(boolean hasName) {
        Player.hasName = hasName;
    }

    public static boolean ship;

    public static boolean isShip() {
        return ship;
    }

    public static void setShip(boolean ship) {
        Player.ship = ship;
    }


    public String direction;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String name;

    private final Backpack backpack;

    public void setPlayerHealth (int playerHealth){
        Player.playerHealth = playerHealth;
        System.out.println("Your health is:" + playerHealth);
    }
    public int getPlayerHealth() {
        return playerHealth;
    }


    private static final BodyImage image =
            new BodyImage("data/pIdle.gif", 5.5f);

    private static final BodyImage left =
            new BodyImage("data/pRunL.gif", 5.5f);

    private static final BodyImage right =
            new BodyImage("data/pRunR.gif", 5.5f);

    private static final BodyImage lev4char =
            new BodyImage("data/shipRight.png", 4.5f);




    public Player(World world) {
        super(world, playerShape);
        addImage(image);
        playerHealth = 3;
        coinsCollected = 0;
        hasName = false;

        backpack = new Backpack();

        if (getPlayerHealth() == 0) {
            game.setGameOver(true);
        }
        if(ship){
        removeAllImages();
        addImage(lev4char);
        setGravityScale(0);
        }
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public void startWalking(float speed){
        super.startWalking(speed);
        if (speed < 0){
            this.removeAllImages();
            this.addImage(left);
            direction = "left";
        }
        else{
            this.removeAllImages();
            this.addImage(right);
            direction = "right";
        }
    }

    public Backpack getBackpack(){
        return backpack;
    }


    public void shoot(Vec2 t){

        DynamicBody projectile = new DynamicBody(this.getWorld(), new CircleShape(0.2f));
        ProjectileCollision projectileCollision = new ProjectileCollision(this);
        projectile.addCollisionListener(projectileCollision);
        Vec2 dir = t.sub(this.getPosition());
        dir.normalize();
        projectile.setGravityScale(0);

       if(direction.equals("left")) {
            projectile.addImage(new BodyImage("data/blast.png"));
       }
       else{
            projectile.addImage(new BodyImage("data/blast.png"));
        }
        projectile.setPosition(this.getPosition().add(dir.mul(1f)));
        projectile.setLinearVelocity(dir.mul(30));

   }
    public void shipShoot(Vec2 t){
        DynamicBody projectile = new DynamicBody(this.getWorld(), new CircleShape(0.2f));
        ProjectileCollision projectileCollision = new ProjectileCollision(this);
        projectile.addCollisionListener(projectileCollision);
        Vec2 dir = t.sub(this.getPosition());
        dir.normalize();
        projectile.setGravityScale(0);
        projectile.setPosition(this.getPosition().add(dir.mul(1f)));
        projectile.setLinearVelocity(dir.mul(50));
    }


}

