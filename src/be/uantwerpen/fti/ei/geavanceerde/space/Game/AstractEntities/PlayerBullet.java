package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

/**
 * Abstract class for the player bullets
 */
public abstract class PlayerBullet extends Player{
    /**
     * Speed of the bullet, this way we can change the speed depending on the fps and height of the frame
     * for all the bullets with 1 variable
     */
    public static double speed = -0.5;

    /**
     * Constructor to initialise the coordinates and speed of the player bullet
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public PlayerBullet(double x, double y){
        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        // Set the y coordinate a little bit higher
        // This way we can give the coordinates of the playership as arguments
        // and the bullet will not spawn on top of the playership
        movementComponent.setY(y-0.4);
        // initial speed
        movementComponent.setDx(0);
        movementComponent.setDy(speed);
    }
}
