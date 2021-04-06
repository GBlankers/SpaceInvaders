package Visual.J2D.Entities;

import Entities.Abstract.PlayerShip;
import Visual.J2D.J2DEngine;

import java.awt.*;

public class J2DPlayerShip extends PlayerShip {
    private final J2DEngine engine;

    public J2DPlayerShip() {
        super();
        engine = J2DEngine.getInstance();
        this.gameWidth = J2DEngine.gameWidth;
        this.gameHeight = J2DEngine.gameHeight;
    }

    @Override
    public void visualise(){
        Graphics2D g2d = engine.getG2d();
        int size = engine.getSize();
        g2d.drawImage(engine.imagePlayerShip, (int) movementComponent.getX()*size, (int) movementComponent.getY()*size, null);
    }
}
