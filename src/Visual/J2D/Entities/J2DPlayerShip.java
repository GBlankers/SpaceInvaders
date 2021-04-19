package Visual.J2D.Entities;

import Entities.Abstract.PlayerShip;
import Visual.J2D.J2DEngine;

import java.awt.*;

public class J2DPlayerShip extends PlayerShip {
    private final J2DEngine engine;

    public J2DPlayerShip() {
        super();
        engine = J2DEngine.getInstance();
    }

    @Override
    public void visualise(){
        Graphics2D g2d = engine.getG2d();
        double size = engine.getSize();
        int x = (int) (movementComponent.getX()*size);
        int y = (int) (movementComponent.getY()*size);
        g2d.drawImage(engine.imagePlayerShip, x, y, null);

        for(int i = 0; i< this.health; i++){
            g2d.drawImage(engine.healthImage, (int) (i*size/3), 0, null);
        }
    }
}
