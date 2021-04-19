package Entities.Abstract;

import Components.MovementComponent;

public abstract class PlayerShip extends Player {
    protected int health;

    public PlayerShip(){
        movementComponent = new MovementComponent();
        movementComponent.setX((4));
        movementComponent.setY((6));
        health = 3;
    }

    public void setDirection(double dx, double dy){
        movementComponent.setDx(dx);
        movementComponent.setDy(dy);
    }

    public void hit(){
        health --;
    }

    public void regeneration(){
        health ++;
    }

    public int getHealth() {
        return health;
    }
}