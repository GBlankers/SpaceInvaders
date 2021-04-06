package Entities.Abstract;

import Components.MovementComponent;

public abstract class PlayerBullet extends Player{
    public PlayerBullet(double x, double y){
        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        movementComponent.setY(y-1);
        movementComponent.setDx(0);
        movementComponent.setDy(-1);
    }
}
