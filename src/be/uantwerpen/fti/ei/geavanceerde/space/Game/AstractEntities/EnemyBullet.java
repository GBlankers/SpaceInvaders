package be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;

public abstract class EnemyBullet extends Enemy{
    public EnemyBullet(double x, double y){
        movementComponent = new MovementComponent();
        movementComponent.setX(x);
        movementComponent.setY(y+1);
        movementComponent.setDx(0);
        movementComponent.setDy(0.2);
    }
}
