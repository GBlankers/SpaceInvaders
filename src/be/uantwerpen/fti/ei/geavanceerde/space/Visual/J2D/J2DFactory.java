package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D;

// Abstract
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory.AbstractFactory;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;
// Java 2D
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Input.KeyboardInput;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DEnemyBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DEnemyShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DPlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DPlayerShip;

import java.awt.*;

/**
 * Java 2D factory to use the methods defined in the abstract factory to create J2D entities,
 * control the J2D engine settings and update the visual game information in a J2D frame
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
     * Initialise the J2D engine
     */
    @Override
    public void createEngine() {
        J2DEngine.getInstance();
    }

    /**
     * Render all the objects to the main J2D frame
     */
    @Override
    public void engineRender() {
        J2DEngine.getInstance().render();
    }

    /**
     * Start the engine
     */
    @Override
    public void engineStart() {
        J2DEngine.getInstance().start();
    }

    /**
     * Give the game coordinates to the visual engine to scale the images
     * @param width max game x coordinate
     * @param height max game y coordinate
     */
    @Override
    public void engineSetGameDimensions(int width, int height) {
        J2DEngine.getInstance().setGameDimensions(width, height);
    }

    /**
     * Make an input class to handle the inputs coming from the keyboard
     * @return an input handler for the J2D frame
     */
    @Override
    public Input createInput() {
        return new KeyboardInput(J2DEngine.getInstance());
    }

    /**
     * Visualise the score on the J2D frame as text
     * @param score current player score
     */
    @Override
    public void updateScore(int score) {
        Graphics2D g = J2DEngine.getInstance().getG2d();
        String text = "Score: " + score;
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 255, 255));
        g.drawString(text, 20, J2DEngine.getInstance().screenHeight - 2*metrics.getHeight());
    }

    /**
     * Display text to inform the player the game has ended with a win
     */
    @Override
    public void gameOverWin() {
        String text = "You Win!";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(0, 255, 0));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString("You win!", x, y);
    }

    /**
     * Display text to inform the player the game has ended with a loss
     */
    @Override
    public void gameOverLose() {
        String text = "You Lose!";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 0, 0));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString(text, x, y);
    }

    /**
     * Display text to inform the player the next level is going to start
     */
    @Override
    public void nextLevel() {
        String text = "Press space to continue";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 255, 255));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString(text, x, y);
    }
}
