package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

/**
 * Abstract class for the enemy bullets
 */
public abstract class EnemyBullet extends Enemy{
    /**
     * Speed of the bullet, this way we can change the speed depending on the fps and height of the frame
     * for all the bullets with 1 variable
     */
    public static double speed = 0.2;

    /**
     * Constructor to initialise the position of the bullet and to initialise the speed
     * @param x initial x coordinate
     * @param y initial y coordinate
     */
    public EnemyBullet(double x, double y){
        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        // Set the Y coordinate a little bit higher, this way we can give the coordinates of
        // the enemy ship as argument and the bullet will spawn below the enemy ship
        movementComponent.setY(y+0.4);
        // initial speed
        movementComponent.setDx(0);
        movementComponent.setDy(speed);
    }
}
