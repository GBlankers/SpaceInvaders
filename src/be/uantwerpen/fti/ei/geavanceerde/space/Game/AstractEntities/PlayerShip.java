package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

/**
 * abstract class of the player ship
 */
public abstract class PlayerShip extends Player {
    /**
     * health of the player
     */
    protected int health;
    /**
     * How much of 1 game coordinate does 1 player ship take up in the x direction.
     * Used for collision detection
     */
    public static double gameWidth;
    /**
     * How much of 1 game coordinate does 1 enemy ship take up in the y direction.
     * Used for collision detection
     */
    public static double gameHeight;

    /**
     * Constructor to initialise initial coordinates, the initial speed and the health
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public PlayerShip(double x, double y){
        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        movementComponent.setY(y);
        health = 3;
    }

    /**
     * Change the direction of the player ship
     * @param dx speed in the x direction
     * @param dy speed in the y direction
     */
    public void setDirection(double dx, double dy){
        movementComponent.setDx(dx);
        movementComponent.setDy(dy);
    }

    /**
     * Player is hit, reduce the health
     */
    public void hit(){
        health --;
    }

    /**
     * Player picks up a bonus to regenerate, health +1
     */
    public void regeneration(){
        health ++;
    }

    /**
     * How much health left
     * @return the health of the player
     */
    public int getHealth() {
        return health;
    }
}