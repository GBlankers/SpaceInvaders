package be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.*;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.EngineControl;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.GameInfo;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;

/**
 * Abstract factory used for abstract entity creation, controlling settings of the visual engine
 * and updating visual game information (game over, score, ...)
 */
public abstract class AbstractFactory {

    // Entities
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
     * Create an engine control class to control the running engine (initialise, start, render and set dimensions)
     * @return an engine control class
     */
    public abstract EngineControl createEngineControl();

    // Player Input
    /**
     * Make an input class to handle the player inputs
     * @return an appropriate input handler for the visual implementation
     */
    public abstract Input createInput();

    // Game info
    /**
     * Create a game info class, this class will handle game messages to the user
     * @return a game info class
     */
    public abstract GameInfo createGameInfo();
}
