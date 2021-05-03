package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

/**
 * The top parent of all the entities, has some default methods every entities uses
 */
public abstract class Entity {

    /**
     * Movement component contains the coordinates and speed of one entity
      */
    protected MovementComponent movementComponent;

    /**
     * Get the movement component of the entity
     * @return a movement component
     */
    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    /**
     * Set the movement component of an entity
     * @param movementComponent the movement component to set
     */
    public void setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    /**
     * Visualise the entity in the engine
     */
    public abstract void visualise();
}
