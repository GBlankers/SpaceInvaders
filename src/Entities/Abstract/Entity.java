package Entities.Abstract;

import Components.MovementComponent;

public abstract class Entity {
    protected int gameWidth;
    protected int gameHeight;

    protected MovementComponent movementComponent;

    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    public void setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    public abstract void visualise();
}
