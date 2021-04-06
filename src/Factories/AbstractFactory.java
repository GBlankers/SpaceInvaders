package Factories;

import Entities.Abstract.EnemyShip;
import Entities.Abstract.PlayerBullet;
import Entities.Abstract.PlayerShip;
import Interaction.Input;
import Visual.Engine;

public abstract class AbstractFactory {

    // Entity creation
    public abstract PlayerShip createPlayerShip();
    public abstract EnemyShip createEnemyShip(double x, double y);
    public abstract PlayerBullet createPlayerBullet(double x, double y);

    // Engine control
    public abstract Engine createEngine();
    public abstract void engineRender();
    public abstract void engineStart();
    public abstract void engineSetGameDimensions(int width, int height);

    // Player Input
    public abstract Input createInput();

    // Score keeping
    public abstract void updateScore(int score);

    // Game end
    public abstract void gameOverWin();
    public abstract void gameOverLose();
}
