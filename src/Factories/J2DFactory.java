package Factories;

import Entities.Abstract.EnemyBullet;
import Entities.Abstract.EnemyShip;
import Entities.Abstract.PlayerBullet;
import Entities.Abstract.PlayerShip;
import Interaction.Input;
import Interaction.KeyboardInput;
import Visual.Engine;
import Visual.J2D.Entities.J2DEnemyBullet;
import Visual.J2D.Entities.J2DEnemyShip;
import Visual.J2D.Entities.J2DPlayerBullet;
import Visual.J2D.Entities.J2DPlayerShip;
import Visual.J2D.J2DEngine;

import java.awt.*;


public class J2DFactory extends AbstractFactory{

    @Override
    public PlayerShip createPlayerShip(){
        return new J2DPlayerShip();
    }

    @Override
    public EnemyShip createEnemyShip(double x, double y) {
        return new J2DEnemyShip(x, y);
    }

    @Override
    public PlayerBullet createPlayerBullet(double x, double y) {
        return new J2DPlayerBullet(x, y);
    }

    @Override
    public EnemyBullet createEnemyBullet(double x, double y) {
        return new J2DEnemyBullet(x, y);
    }

    @Override
    public Engine createEngine() {
        return J2DEngine.getInstance();
    }

    @Override
    public void engineRender() {
        J2DEngine.getInstance().render();
    }

    @Override
    public void engineStart() {
        J2DEngine.getInstance().start();
    }

    @Override
    public void engineSetGameDimensions(int width, int height) {
        J2DEngine.getInstance().setGameDimensions(width, height);
    }

    @Override
    public Input createInput() {
        return new KeyboardInput(J2DEngine.getInstance());
    }

    @Override
    public void updateScore(int score) {
        Graphics2D g = J2DEngine.getInstance().getG2d();
        String text = "Score: " + score;
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        g.setColor(new Color(255, 255, 255));
        g.drawString(text, 20, (J2DEngine.gameHeight*J2DEngine.getInstance().getSize()) - 10);
    }

    @Override
    public void gameOverWin() {
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        g.setColor(new Color(0, 255, 0));
        int x = (J2DEngine.gameWidth*J2DEngine.getInstance().getSize()/2) - J2DEngine.gameWidth*J2DEngine.getInstance().getSize()/7;
        int y = (J2DEngine.gameHeight*J2DEngine.getInstance().getSize()/2);
        g.drawString("You win!", x, y);
    }

    @Override
    public void gameOverLose() {
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        g.setColor(new Color(255, 0, 0));
        int x = (J2DEngine.gameWidth*J2DEngine.getInstance().getSize()/2) - J2DEngine.gameWidth*J2DEngine.getInstance().getSize()/7;
        int y = (J2DEngine.gameHeight*J2DEngine.getInstance().getSize()/2);
        g.drawString("You lose!", x, y);
    }

    @Override
    public double getEntitySize(){
        return J2DEngine.getInstance().getEntitySize();
    }
}
