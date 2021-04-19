package Entities.Abstract;

import Components.MovementComponent;

public abstract class EnemyShip extends Enemy{

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

//    // Change
//    public void setDir(){
//        // kom aan de linker rand met snelheid naar links => beweeg naar onder
//        if(this.movementComponent.getX() == 0 & this.movementComponent.getDx() == -1){
//            this.movementComponent.setDx(0);
//            this.movementComponent.setDy(1);
//        // Kom aan de rechter kant met een snelheid naar rechts
//        } else if(this.movementComponent.getX() == this.gameWidth-1 & this.movementComponent.getDx() == 1){
//            this.movementComponent.setDx(0);
//            this.movementComponent.setDy(1);
//        // Net naar beneden bewogen aan rechter kant
//        } else if(this.movementComponent.getX() == this.gameWidth-1 & this.movementComponent.getDy()== 1){
//            this.movementComponent.setDx(-1);
//            this.movementComponent.setDy(0);
//        } else if(this.movementComponent.getX() == 0 & this.movementComponent.getDy() == 1){
//            this.movementComponent.setDx(1);
//            this.movementComponent.setDy(0);
//        }
//    }
}
