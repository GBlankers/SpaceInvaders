package Entities.Abstract;

import Components.MovementComponent;

public abstract class PlayerShip extends Player {

    public PlayerShip(){
        movementComponent = new MovementComponent();
        movementComponent.setX((4));
        movementComponent.setY((6));
    }

    public void setDirection(double dx, double dy){
        if(movementComponent.getX() + dx < 0) {
            movementComponent.setDx(0);
            movementComponent.setDy(dy);
        } else if(movementComponent.getX() + dx > this.gameWidth-1) {
            movementComponent.setDx(0);
            movementComponent.setDy(dy);
        } else if (movementComponent.getY() + dy < 0) {
            movementComponent.setDx(dx);
            movementComponent.setDy(0);
        } else if(movementComponent.getY() + dy > this.gameHeight-1){
            movementComponent.setDx(dx);
            movementComponent.setDy(0);
        } else {
            movementComponent.setDx(dx);
            movementComponent.setDy(dy);
        }
    }
}