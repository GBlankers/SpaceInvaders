package be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.Engine;

public abstract class AbstractFactory {

    // Entity creation
    /**
     * Create a new player ship
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new player ship at the given coordinates
     */
    public abstract PlayerShip createPlayerShip(double x, double y);

    /**
     * Create a new enemy ship
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new enemy ship at the given coordinates
     */
    public abstract EnemyShip createEnemyShip(double x, double y);

    /**
     * Create a new player bullet
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new player bullet at the given coordinates
     */
    public abstract PlayerBullet createPlayerBullet(double x, double y);

    /**
     * Create a new enemy bullet
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new enemy bullet at the given coordinates
     */
    public abstract EnemyBullet createEnemyBullet(double x, double y);

    // Engine control
    /**
     * Create the engine to visualise the game
     * @return an engine to visualise
     */
    public abstract Engine createEngine();

    /**
     * Render all the objects that have been drawn to the current frame
     */
    public abstract void engineRender();

    /**
     * Start the engine
     */
    public abstract void engineStart();

    /**
     * Change the coordinates
     * @param width max game x coordinate
     * @param height max game y coordinate
     */
    public abstract void engineSetGameDimensions(int width, int height);

    // Player Input
    /**
     * Make an input class to handle the player inputs
     * @return an appropriate input handler for the visual implementation
     */
    public abstract Input createInput();

    // Score keeping
    /**
     * Visualise the score on the screen
     * @param score current player score
     */
    public abstract void updateScore(int score);

    // Game end
    /**
     * Visualise the game over win state
     */
    public abstract void gameOverWin();

    /**
     * Visualise the game over lose state
     */
    public abstract void gameOverLose();

    // Next Level message
    /**
     * Display a message to go to the next level
     */
    public abstract void nextLevel();

    // Entity Size
    /**
     * Get the size of the entities to make collision detection possible
     * @return the size of the entities in the visual part
     */
    public abstract double getEntitySize();
}
