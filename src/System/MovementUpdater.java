package System;

import Components.MovementComponent;

public class MovementUpdater {

    public static MovementComponent update(MovementComponent component){
        component.setX(component.getDx() + component.getX());
        component.setY(component.getDy() + component.getY());

        return component;
    }
}