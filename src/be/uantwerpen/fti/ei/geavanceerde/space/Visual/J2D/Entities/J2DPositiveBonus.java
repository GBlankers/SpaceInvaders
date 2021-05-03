package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PositiveBonus;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

/**
 * J2D version of the positive bonus
 */
public class J2DPositiveBonus extends PositiveBonus {
    private final J2DEngine engine;

    /**
     * Constructor to set the coordinates
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public J2DPositiveBonus(double x, double y){
        super(x, y);
        engine = J2DEngine.getInstance();
    }

    @Override
    public void visualise() {
        Graphics2D g2d = engine.getG2d();

        // Place the bonus in the middle of the square grid
        int x = (int) (movementComponent.getX()*engine.sizeX + engine.enemyBullet.getWidth()/2);
        int y = (int) (movementComponent.getY()*engine.sizeY - engine.enemyBullet.getHeight()/2);

        g2d.drawImage(engine.playerBullet, x, y, null);
    }
}
