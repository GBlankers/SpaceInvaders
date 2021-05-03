package be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.*;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;

/**
 * Abstract factory used for abstract entity creation, controlling settings of the visual engine
 * and updating visual game information (game over, score, ...)
 */
public abstract class AbstractFactory {

    /**
     * Create a new abstract player ship
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new abstract player ship at the given coordinates
     */
    public abstract PlayerShip createPlayerShip(double x, double y);

    /**
     * Create a new abstract enemy ship
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new abstract enemy ship at the given coordinates
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

    /**
     * Create a new positive bonus
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new positive bonus at the given coordinates
     */
    public abstract PositiveBonus createPositiveBonus(double x, double y);

    // Engine control
    /**
     * Initialise the engine to visualise the game
     */
    public abstract void createEngine();

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
}
