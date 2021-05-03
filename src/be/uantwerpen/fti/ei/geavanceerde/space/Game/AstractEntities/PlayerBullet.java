package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

public abstract class PlayerBullet extends Player{
    public PlayerBullet(double x, double y){
        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        movementComponent.setY(y-0.4);
        movementComponent.setDx(0);
        movementComponent.setDy(-0.5);
    }
}
