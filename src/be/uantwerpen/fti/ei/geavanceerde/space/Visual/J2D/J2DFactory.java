package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D;

// Abstract
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.*;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory.AbstractFactory;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.EngineControl;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.GameInfo;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;
// Java 2D
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.*;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Interaction.J2DEngineControl;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Interaction.J2DGameInfo;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Interaction.J2DKeyboardInput;


/**
 * Java 2D factory to use the methods defined in the abstract factory to create J2D objects.
 * These objects can be entities, object to control the engine or to show info in the J2D frame
 */
public class J2DFactory extends AbstractFactory {

    /**
     * Create a new J2D player ship, this will have a visualise method for the J2D engine
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new player ship at the given coordinates
     */
    @Override
    public PlayerShip createPlayerShip(double x, double y){
        return new J2DPlayerShip(x, y);
    }

    /**
     * Create a new J2D enemy ship, this will have a visualise method for the J2D engine
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new enemy ship at the given coordinates
     */
    @Override
    public EnemyShip createEnemyShip(double x, double y) {
        return new J2DEnemyShip(x, y);
    }

    /**
     * Create a new J2D player bullet, this will have a visualise method for the J2D engine
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new player bullet at the given coordinates
     */
    @Override
    public PlayerBullet createPlayerBullet(double x, double y) {
        return new J2DPlayerBullet(x, y);
    }

    /**
     * Create a new J2D enemy bullet, this will have a visualise method for the J2D engine
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new enemy bullet at the given coordinates
     */
    @Override
    public EnemyBullet createEnemyBullet(double x, double y) {
        return new J2DEnemyBullet(x, y);
    }

    /**
     * Create a new J2D positive bonus, this will have a visualise method for the J2D engine
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a new positive bonus at the given coordinates
     */
    @Override
    public PositiveBonus createPositiveBonus(double x, double y) {
        return new J2DPositiveBonus(x, y);
    }

    /**
     * Create an engine control class for the J2D engine to initialise, start, render and set the game dimensions
     * @return a J2D Engine control class
     */
    @Override
    public EngineControl createEngineControl() {
        return new J2DEngineControl();
    }

    /**
     * Make an input class to handle the inputs coming from the keyboard
     * @return an input handler for the J2D frame
     */
    @Override
    public Input createInput() {
        return new J2DKeyboardInput(J2DEngine.getInstance());
    }

    /**
     * Create a J2D game info class to show game info to the user in the J2D panel
     * @return a J2D Game info class
     */
    @Override
    public GameInfo createGameInfo(){
        return new J2DGameInfo();
    }
}
