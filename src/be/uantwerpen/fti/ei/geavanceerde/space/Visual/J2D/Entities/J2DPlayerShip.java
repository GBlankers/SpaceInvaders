package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

public class J2DPlayerShip extends PlayerShip {
    private final J2DEngine engine;

    public J2DPlayerShip(double x, double y) {
        super(x, y);
        engine = J2DEngine.getInstance();
    }

    @Override
    public void visualise(){
        Graphics2D g2d = engine.getG2d();
        double size = engine.getSize();

        int x = (int) (movementComponent.getX()*engine.sizeX);
        int y = (int) ((movementComponent.getY()*engine.sizeY) - engine.imagePlayerShip.getHeight()/2);
        g2d.drawImage(engine.imagePlayerShip, x, y, null);

        for(int i = 0; i< this.health; i++){
            g2d.drawImage(engine.healthImage, (int) (i*size/3), 0, null);
        }
    }
}
