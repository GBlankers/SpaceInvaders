package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

/**
 * J2D version of the enemy bullet
 */
public class J2DEnemyBullet extends EnemyBullet {
    // save the engine to scale the movements to visual engine coordinates
    private final J2DEngine engine;

    /**
     * Constructor to set the coordinates
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public J2DEnemyBullet(double x, double y){
        super(x, y);
        engine = J2DEngine.getInstance();
    }

    /**
     * Method to visualise the enemy bullet in the J2D engine
     */
    @Override
    public void visualise() {
        Graphics2D g2d = engine.getG2d();

        // Place the bullet in the middle of the square grid
        int x = (int) (movementComponent.getX()*engine.sizeX + engine.enemyBullet.getWidth()/2);
        int y = (int) (movementComponent.getY()*engine.sizeY - engine.enemyBullet.getHeight()/2);

        g2d.drawImage(engine.enemyBullet, x, y, null);
    }
}
