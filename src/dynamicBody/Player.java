package dynamicBody;

import city.cs.engine.*;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import game.Game;
import game.GameLevel;
import game.levels.Level4;
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

    //Level 1-3 character idle, left and right facing images
    private static final BodyImage image =
            new BodyImage("data/pIdle.gif", 5.5f);
    private static final BodyImage left =
            new BodyImage("data/pRunL.gif", 5.5f);
    private static final BodyImage right =
            new BodyImage("data/pRunR.gif", 5.5f);
    //Level 4 ship
    private static final BodyImage lev4char =
            new BodyImage("data/shipRight.png", 4.5f);



    public static boolean hasName; //Check if player has a name
    public static boolean ship; //Boolean for making player a ship in final level
    public boolean invincible; //Making player invincible so can have invincibility frames after being hit
    public boolean jumped = false; //Checking if player has jumped so can double jump

    private static Backpack backpack;
    public String name;
    public String direction;
    public Vec2 playerShotPos;
    public int shotgunShells = 10;
    private static int coinsCollected;
    public static int playerHealth;
    public static int timeFired = 0; //This is used to implement a small delay between each shot in final level



    //Getters and setters. Coins and health also print
    public static void setCoins(int coinsCollected){
        Player.coinsCollected = coinsCollected;
        System.out.println("Your score is: " + coinsCollected);
    }
    public static int getCoinsCollected() {
        return coinsCollected;
    }

    public  boolean isHasName() {
        return hasName;
    }
    public void setHasName(boolean hasName) {
        Player.hasName = hasName;
    }

    public static boolean isShip() {
        return ship;
    }
    public static void setShip(boolean ship) {
        Player.ship = ship;
    }

    public boolean isInvincible() {
        return invincible;
    }
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerHealth (int playerHealth){
        Player.playerHealth = playerHealth;
        System.out.println("Your health is:" + playerHealth);
    }
    public int getPlayerHealth() {
        return playerHealth;
    }

    public boolean isJumped() {
        return jumped;
    }
    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public static Backpack getBackpack(){
        return backpack;
    }

    public String getDirection() {
        return direction;
    }

    //Default player variables
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

        //Makes the player the ship on final level. No gravity (in space!) and different image
        if(ship){
        removeAllImages();
        addImage(lev4char);
        setGravityScale(0);
        }
    }

    //Walking method and also can change image depending on direction
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



    //Shooting methods
    public void shoot(Vec2 t){
        if(!isShip()) { //If the player is NOT the ship (so levels 1-3)
            if (Player.getBackpack().getCurrentItem().getType() == "Gun") { //And if the gun is current item
                //A lot of this could be put into it's own class. Laziness from me
                DynamicBody projectile = new DynamicBody(this.getWorld(), new CircleShape(0.2f));
                ProjectileCollision projectileCollision = new ProjectileCollision(this);
                projectile.addCollisionListener(projectileCollision); //Listeners
                Vec2 dir = t.sub(this.getPosition()); //Creating a vector
                dir.normalize();
                projectile.setGravityScale(0);
                projectile.addImage(new BodyImage("data/blast.gif"));
                projectile.setPosition(this.getPosition().add(dir.mul(1f)));
                projectile.setLinearVelocity(dir.mul(30));
            }
            else if (Player.getBackpack().getCurrentItem().getType() == "Shotgun")
            {
                if (shotgunShells > 0) //Shotgun has 10 shots, only fire when more than 0
                {
                    for (int i = 1; i < 5; i++) //Fires 5 shots
                    {
                        DynamicBody projectile = new DynamicBody(this.getWorld(), new CircleShape(0.2f));
                        ProjectileCollision projectileCollision = new ProjectileCollision(this);
                        projectile.addCollisionListener(projectileCollision);
                        Vec2 dir = t.sub(new Vec2(this.getPosition().x + 2 * i, this.getPosition().y + 2 * i));
                        dir.normalize();
                        projectile.setGravityScale(0);
                        projectile.addImage(new BodyImage("data/blast.png"));
                        projectile.setPosition(this.getPosition().add(dir.mul(1f)));
                        projectile.setLinearVelocity(dir.mul(30));

                        shotgunShells = shotgunShells - 1;

                        //Ignore this variable, was used when attempting to make the bullets disappear
                        playerShotPos = getPosition();

                  /*     This is me trying to make shotgun bullets disappear after being a certain distance
                        away. Left it in to refer back to in the future
                if ((projectile.getPosition().sub(playerShotPos)).x > 3){
                    if ((projectile.getPosition().sub(playerShotPos)).y > 3){
                        projectile.destroy();
                    }
                if (Math.sqrt((projectile.getPosition().x * projectile.getPosition().x)
                                + (projectile.getPosition().y * projectile.getPosition().y)) -
                                Math.sqrt((playerShotPos.x * playerShotPos.x)
                                        + (playerShotPos.y * playerShotPos.y)) > 0) {
                            projectile.destroy();

                        }*/
                    }

                }
            }
        }
   }
    public void shipShoot(Player player, GameLevel level){
        /*If the current time minus the time fired is greater than 5, you can fire another shot*/
        if(Level4.getSpawn()-timeFired>5) {
            Projectile projectile = new Projectile(getWorld(), player);
            timeFired = Level4.getSpawn();
        }
    }


}

