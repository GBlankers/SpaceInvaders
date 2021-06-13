package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * J2D version of the enemy ship
 */
public class J2DEnemyShip extends EnemyShip {

    private final J2DEngine engine;
    private final int spriteNumber;

    /**
     * Constructor to set the coordinates
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public J2DEnemyShip(double x, double y){
        super(x, y);
        engine = J2DEngine.getInstance();
        // random sprite number to get a random color enemy
        spriteNumber = ThreadLocalRandom.current().nextInt(0, 7);
    }

    /**
     * Method to visualise the enemy ship in the J2D engine
     */
    @Override
    public void visualise() {
        Graphics2D g2d = engine.getG2d();
        // Engine image size
        int imageSize = engine.getImageSize();

        int x = (int) (movementComponent.getX()*engine.sizeX);
        int y = (int) (movementComponent.getY()*engine.sizeY);

        g2d.drawImage(engine.enemySprite.getSubimage( spriteNumber*imageSize, 0, imageSize, imageSize), x, y, null);
    }
}
