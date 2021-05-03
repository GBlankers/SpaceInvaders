package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

public abstract class EnemyShip extends Enemy{
    public static double gameWidth;
    public static double gameHeight;

    public EnemyShip(double x, double y){
        super();
        movementComponent = new MovementComponent();
        this.movementComponent.setX((x+4));
        this.movementComponent.setY((y+3));
        this.movementComponent.setDx(-1);
    }

    public void setMovement(double dx, double dy){
        this.movementComponent.setDx(dx);
        this.movementComponent.setDy(dy);
    }
}
