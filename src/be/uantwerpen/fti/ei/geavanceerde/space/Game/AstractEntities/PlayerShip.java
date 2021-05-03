package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

public abstract class PlayerShip extends Player {
    protected int health;
    public static double gameWidth;
    public static double gameHeight;

    public PlayerShip(double x, double y){

        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        movementComponent.setY(y);
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