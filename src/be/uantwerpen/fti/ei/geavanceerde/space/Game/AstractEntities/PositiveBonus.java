package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

/**
 * Abstract class for positive bonuses
 */
public abstract class PositiveBonus extends Entity{
    /**
     * Speed of the bonus, this way we can change the speed depending on the fps and height of the frame
     * for all the bonuses with 1 variable
     */
    public static double speed = 0.2;

    /**
     * Constructor to initialise the position of the bonus and to initialise the speed
     * @param x initial x coordinate
     * @param y initial y coordinate
     */
    public PositiveBonus(double x, double y){
        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        movementComponent.setY(y+0.4);
        movementComponent.setDx(0);
        movementComponent.setDy(speed);
    }
}
