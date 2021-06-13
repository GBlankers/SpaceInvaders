package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

/**
 * J2D version of the player ship
 */
public class J2DPlayerShip extends PlayerShip {
    private final J2DEngine engine;

    /**
     * Constructor to set the coordinates
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public J2DPlayerShip(double x, double y) {
        super(x, y);
        engine = J2DEngine.getInstance();
    }

    /**
     * Method to visualise the player ship in the J2D engine
     */
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
