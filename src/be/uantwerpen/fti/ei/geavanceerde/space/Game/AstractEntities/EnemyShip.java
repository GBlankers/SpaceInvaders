package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

/**
 * Abstract class for the enemy ship entity
 */
public abstract class EnemyShip extends Enemy{
    /**
     * How much of 1 game coordinate does 1 enemy ship take up in the x direction.
     * Used for collision detection
     */
    public static double gameWidth;
    /**
     * How much of 1 game coordinate does 1 enemy ship take up in the y direction.
     * Used for collision detection
     */
    public static double gameHeight;

    /**
     * Constructor to initialise initial coordinates and the initial speed
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public EnemyShip(double x, double y){
        super();
        movementComponent = new MovementComponent();
        this.movementComponent.setX((x));
        this.movementComponent.setY((y));
        this.movementComponent.setDx(-1);
    }

    /**
     * Change the speed in both directions
     * @param dx the new speed in the x direction
     * @param dy the new speed in the y direction
     */
    public void setMovement(double dx, double dy){
        this.movementComponent.setDx(dx);
        this.movementComponent.setDy(dy);
    }
}
