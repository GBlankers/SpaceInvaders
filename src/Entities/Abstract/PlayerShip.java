package Entities.Abstract;

import Components.MovementComponent;

public abstract class PlayerShip extends Player {

    public PlayerShip(){
        movementComponent = new MovementComponent();
        movementComponent.setX((4));
        movementComponent.setY((6));
    }

    public void setDirection(double dx, double dy){
        movementComponent.setDx(dx);
        movementComponent.setDy(dy);
    }
}