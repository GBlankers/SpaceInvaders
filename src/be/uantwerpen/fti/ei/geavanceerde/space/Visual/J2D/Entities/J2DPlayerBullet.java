package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

public class J2DPlayerBullet extends PlayerBullet {
    private final J2DEngine engine;

    public J2DPlayerBullet(double x, double y){
        super(x, y);
        engine = J2DEngine.getInstance();
    }

    @Override
    public void visualise() {
        Graphics2D g2d = engine.getG2d();

        int x = (int) (movementComponent.getX()*engine.sizeX+ engine.sizeX/4);
        int y = (int) (movementComponent.getY()*engine.sizeY-engine.playerBullet.getHeight());

        // Place the bullet in the middle of the square grid
        g2d.drawImage(engine.playerBullet, x, y, null);
    }
}
