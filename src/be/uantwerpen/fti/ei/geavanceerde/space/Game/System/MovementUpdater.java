package be.uantwerpen.fti.ei.geavanceerde.space.Game.System;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

/**
 * Class to update the movement components
 */
public class MovementUpdater {

    /**
     * Update the coordinates in a movement component using the speed vector in this
     * movement component
     * @param component the movement component to update
     * @return the updated movement component
     */
    public static MovementComponent update(MovementComponent component){
        component.setX(component.getDx() + component.getX());
        component.setY(component.getDy() + component.getY());
        return component;
    }
}