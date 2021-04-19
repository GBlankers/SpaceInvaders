package Visual.J2D.Entities;

import Entities.Abstract.EnemyBullet;
import Visual.J2D.J2DEngine;

import java.awt.*;

public class J2DEnemyBullet extends EnemyBullet {
    private final J2DEngine engine;

    public J2DEnemyBullet(double x, double y){
        super(x, y);
        engine = J2DEngine.getInstance();
    }

    @Override
    public void visualise() {
        Graphics2D g2d = engine.getG2d();
        double size = engine.getSize();

        int x = (int) (movementComponent.getX()*size+size/4);
        int y = (int) (movementComponent.getY()*size+size/4);

        // Place the bullet in the middle of the square grid
        g2d.drawImage(engine.enemyBullet, x, y, null);
    }
}
