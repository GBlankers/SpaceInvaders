package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

public class J2DPlayerBullet extends PlayerBullet {
    private final J2DEngine engine;

    /**
     * Constructor to set the coordinates
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public J2DPlayerBullet(double x, double y){
        super(x, y);
        engine = J2DEngine.getInstance();
    }

    /**
     * Method to visualise the Player bullet in the J2D engine
     */
    @Override
    public void visualise() {
        Graphics2D g2d = engine.getG2d();

        // Place the bullet in the middle of the square grid
        int x = (int) (movementComponent.getX()*engine.sizeX+ engine.sizeX/4);
        int y = (int) (movementComponent.getY()*engine.sizeY-engine.playerBullet.getHeight());

        g2d.drawImage(engine.playerBullet, x, y, null);
    }
}
